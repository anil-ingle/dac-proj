package com.dac.onlineparking.module.login;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dac.onlineparking.module.exception.OnlineParkingGlobalException;

@RestController
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
	private LoginService loginService;
	@Autowired
	private JavaMailSender sender;

	Random rnd = new Random();

	Map<String, Integer> emailCode = new HashMap<String, Integer>();
	Map<Integer, String> verify = new HashMap<Integer, String>();
	Map<String, ForgetPasswordInfoVO> validateUser = new HashMap<String, ForgetPasswordInfoVO>();

	@RequestMapping(path = "mvc/login", method = RequestMethod.GET)
	public @ResponseBody LoginVO login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("un");
		String password = request.getParameter("password");
		HttpSession sess = request.getSession(true);
		LoginStatusVO user = loginService.login(userName, password);
		try {

			if (user.isStatus()) {
				sess.setAttribute("user", user.getLoginVO());
				return user.getLoginVO();
			} else {
				sess.invalidate();

				throw new OnlineParkingGlobalException(user.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OnlineParkingGlobalException(user.getMessage());
		}
	}

	@RequestMapping(value = "mvc/logout", method = RequestMethod.GET)
	public @ResponseBody boolean logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sess = request.getSession(false);
		if (sess != null) {
			sess.invalidate();
		}
		return true;
	}

	/*
	 * 1111 is Success. 1112 is Mail not Found. 1113 is Error while sending mail
	 */
	@RequestMapping(value = "mvc/sendMail", method = RequestMethod.GET)
	public @ResponseBody ForgetResponseVO sendMail(@RequestParam("email") String email) {
		ForgetResponseVO vo = new ForgetResponseVO();
		synchronized (LoginController.class) {
			if (loginService.isEmailValid(email)) {
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				int code = 100000 + rnd.nextInt(900000);
				try {
					ForgetPasswordInfoVO infoVO = new ForgetPasswordInfoVO();
					infoVO.setEmail(email);
					infoVO.setOtp(code);
					String key = UUID.randomUUID().toString().toUpperCase() + Calendar.getInstance().getTimeInMillis();
					infoVO.setKey(key);
					System.out.println("mail method " + key);
					validateUser.put(key, infoVO);
					vo.setKey(key);
					emailCode.put(email, code);
					verify.put(code, email);
					helper.setTo(email);
					helper.setText("We received a request to reset your OnlinePaking password.");
					helper.setText("Your reset code is ." + code);
					helper.setSubject("is your Online Parking account recovery code");
				} catch (MessagingException e) {
					e.printStackTrace();
					// here error code 1113 is Eoor while Sending Mail.
					vo.setCode(1113);
					return vo;
				}
				sender.send(message);
				// here success code is 1111 means mail send successfuly.

				vo.setCode(1111);

				return vo;
			} else {
				// here return 1112 means Mail Not Found
				vo.setCode(1112);
				return vo;
			}
		}
	}

	@RequestMapping(value = "mvc/validateOtp", method = RequestMethod.GET)
	public Boolean validateOtp(@RequestParam("otp") int otp, HttpServletRequest req, HttpServletResponse resp) {
		String key = req.getHeader("fToken");
		if (validateUser.containsKey(key)) {
			if (verify.containsKey(otp)) {
				String email = verify.get(otp);
				int mapOtp = emailCode.get(email);
				if (mapOtp == otp) {
					emailCode.remove(verify.get(otp));
					verify.remove(otp);
					System.out.println("comming....");
					return true;
				} else
					return false;
			} else
				return false;
		} else {
			return false;
		}
	}

	@RequestMapping("mvc/resetPassword")
	public boolean resetPassword(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("newPass") String newPass) {
		String key = req.getHeader("fToken");
		System.out.println("Header resert password " + key);
		if (validateUser.containsKey(key)) {
			ForgetPasswordInfoVO vo = validateUser.get(key);
			return loginService.resetPassword(vo.getEmail(), newPass);
		}
		return false;
	}
}