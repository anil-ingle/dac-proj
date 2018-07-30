package com.dac.onlineparking.module.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public List<CityAreaVO> selectCityArea(int areaId) {
		return userDAO.selectCityArea(areaId);
	}

	public List<UserBookSlotVO> getAreaSlot(Integer areaId) {
		return userDAO.getAreaSlot(areaId);
	}

	public boolean slotBookUsingWolet(WolletBookVO bookVO) throws SQLException {
		int status = 0;

		status = userDAO.slotBookUsingWolet(bookVO);

		if (status == 1) {
			return true;
		} else {
			return false;
		}
	}
}
