package com.dac.onlineparking.module.user;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dac.onlineparking.util.CommonUtil;
import com.dac.onlineparking.util.UserQuery;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CityAreaVO> selectCityArea(int areaId) {
		List<CityAreaVO> areaVOs = jdbcTemplate.query(UserQuery.SLECT_CITY_AREA, (ResultSet rs) -> {
			List<CityAreaVO> areaVOs1 = new ArrayList<CityAreaVO>();
			while (rs.next()) {
				CityAreaVO areaVO = new CityAreaVO();
				areaVO.setAreaId(rs.getInt("area_id"));
				areaVO.setAreaName(rs.getString("area_name"));
				areaVOs1.add(areaVO);
			} // while
			return areaVOs1;
		}// extractData(-)
				, areaId);

		return areaVOs;
	}

	public List<UserBookSlotVO> getAreaSlot(Integer areaId) {
		List<UserBookSlotVO> bookSlotVO = jdbcTemplate.query(UserQuery.SELECT_AREASLOT, (ResultSet rs) -> {
			List<UserBookSlotVO> bookSlotVO1 = new ArrayList<UserBookSlotVO>();
			while (rs.next()) {
				UserBookSlotVO vo = new UserBookSlotVO();
				vo.setfSlotId(rs.getInt("id"));
				vo.setSlotNumber(rs.getInt("slot_number"));
				vo.setIsReserved(rs.getInt("is_reserved"));
				vo.setfSlotId(rs.getInt("fslot_id"));
				bookSlotVO1.add(vo);
			} // while
			return bookSlotVO1;
		}// extractData(-)
				, areaId);

		return bookSlotVO;
	}

	public int slotBookUsingWolet(WolletBookVO bookVO) throws SQLException {
		int statusForWolet = 0;
		boolean paymentFlag = this.userWoletDeduction(bookVO);
		if (paymentFlag) {
			boolean statusRecord = this.recordOwnerWoller(bookVO);
			if (statusRecord) {

				boolean flag = this.slotBook(bookVO.getBookedSlots(), bookVO.getAreaId());

				if (flag) {

					statusForWolet = jdbcTemplate.update(UserQuery.USER_BOOK_HISTORY, bookVO.getUserId(),
							bookVO.getBookedSlots(), bookVO.getAreaId(), bookVO.getwBill(), bookVO.getTimeTaken(),
							new Date() + "");

				}
			}
		}

		return statusForWolet;

	}

	private boolean userWoletDeduction(WolletBookVO bookVO) {
		boolean flag = true;

		// execute the query
		int ret = jdbcTemplate.update(UserQuery.USER_WOLLET_DEDUCTION, (bookVO.getwTotal() - bookVO.getwBill()),
				bookVO.getUserId());

		if (ret == 0) {
			flag = false;

		}

		return flag;
	}

	private boolean recordOwnerWoller(WolletBookVO bookVO) {
		WolletStatusVO statusVO = this.creditOwnerWollet(bookVO);
		boolean statusRecord = false;
		if (statusVO.isSuccess()) {

			// execute the query
			int ret = jdbcTemplate.update(UserQuery.OWNER_RECORD, bookVO.getUserId(), new Date() + "",
					bookVO.getwBill(), statusVO.getOwnerId(), 0);

			if (ret == 1) {
				statusRecord = true;

			}

		}
		return statusRecord;

	}

	private WolletStatusVO creditOwnerWollet(WolletBookVO bookVO) {
		WolletStatusVO statusVO = this.getOwnerWolletAmount(bookVO);
		statusVO.setSuccess(false);
		if (statusVO.getOwnerId() != 0) {

			// execute the query
			int ret = jdbcTemplate.update(UserQuery.CREDIT_OWNER_WOLLET, (statusVO.getAmount() + bookVO.getwBill()),
					statusVO.getOwnerId());

			if (ret == 1) {
				statusVO.setSuccess(true);

			}

		}

		return statusVO;

	}

	private WolletStatusVO getOwnerWolletAmount(WolletBookVO bookVO) {

		// execute the query

		List<WolletStatusVO> list = jdbcTemplate.query(UserQuery.OWNER_WOLET_AMOUNT, (ResultSet rs) -> {
			List<WolletStatusVO> wList = new ArrayList<WolletStatusVO>();
			while (rs.next()) {
				WolletStatusVO vo = new WolletStatusVO();

				vo.setOwnerId(rs.getInt("ownerId"));
				vo.setAmount(rs.getDouble("amount"));
				wList.add(vo);
			} // while
			return wList;
		}// extractData(-)
				, bookVO.getAreaId());
		if (list.size() == 1) {
			return list.get(0);
		} else

			return null;

	}
	

	private boolean slotBook(String bookedSlots, Integer areaId) throws SQLException {
		List<Integer> ids = CommonUtil.csvIds(bookedSlots);
		boolean flag = true;

		for (Integer id : ids) {

			// execute the query
			int ret = jdbcTemplate.update(UserQuery.BOOK_SLOTS, areaId, id);

			if (ret == 0) {
				flag = false;

			}

		}

		return flag;

	}
}
