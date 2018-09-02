package com.dac.onlineparking.module.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	/**
	 * @param HttpServletRequest
	 * @return List<CityVO>
	 * @throws Exception
	 * @author Anil D. Ingle
	 */
	@RequestMapping(value = "mvc/city", method = RequestMethod.GET)
	public @ResponseBody List<CityVO> selectCity(HttpServletRequest request) {
		return adminService.selectCity();
	}

	/**
	 * @param HttpServletRequest
	 * @param RegisterVO
	 * @return boolean
	 * @throws Exception
	 * @author Anil D. Ingle
	 */
	@RequestMapping(value = "mvc/registration", method = RequestMethod.POST)
	public @ResponseBody boolean register(HttpServletRequest request, @RequestBody RegisterVO vo) {
		return adminService.register(vo);
	}

}
