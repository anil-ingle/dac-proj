package com.dac.onlineparking.module.login;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dac.onlineparking.util.UserQuery;

@Repository
public class LoginDAO {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param String
	 * @param String
	 * @return LoginVO
	 * @throws Exception
	 * @author Anil D. Ingle
	 * 
	 */
	public LoginVO login(String userName, String password) {
		try {
			List<LoginVO> listVOs = jdbcTemplate.query(UserQuery.LOGIN_USSER, (ResultSet rs) -> {
				List<LoginVO> list = new ArrayList<>();
				while (rs.next()) {
					LoginVO vo = new LoginVO();
					vo.setId(rs.getInt("id"));
					vo.setfName(rs.getString("fname"));
					vo.setlName(rs.getString("lname"));
					vo.setEmail(rs.getString("email"));
					vo.setRoll(rs.getInt("roll"));
					vo.setGender(rs.getString("gender"));
					vo.setMobileNumber(rs.getString("mobileNumber"));
					vo.setTotalAmount(rs.getDouble("totalamount"));
					list.add(vo);
				} // while
				return list;
			}// extractData(-)
					, userName, password);
			if (listVOs.size() == 1)
				return listVOs.get(0);
			else
				return null;
		} catch (Exception e) {
			log.error("An error occurred while Fetching User login info . Please contact the Support Team.", e);
			throw e;
		}
	}

	/**
	 * @param String
	 * @return Integer
	 * @throws Exception
	 * @author Anil D. Ingle
	 * 
	 */
	public Integer isEmailValid(String email) {
		try {
			Object[] inputs = new Object[] { email };
			return jdbcTemplate.queryForObject(UserQuery.IS_EMAIL_VALID, inputs, Integer.class);
		} catch (Exception e) {
			log.error("An error occurred while Checking user email id  . Please contact the Support Team.", e);
			throw e;
		}

	}

	/**
	 * @param String
	 * @param String
	 * @return int
	 * @throws Exception
	 * @author Anil D. Ingle
	 * 
	 */
	public int resetPassword(String email, String password) {
		try {
			return jdbcTemplate.update(UserQuery.RESET_PASSWORD, password, email);
		} catch (Exception e) {
			log.error("An error occurred while Reset  Password . Please contact the Support Team.", e);
			throw e;
		}
	}
}
