package com.dac.onlineparking.module.owner;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dac.onlineparking.util.OwnerQuery;

@Repository
public class OwnerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OwnerAddressInfoVO> ownerAddress(int userId) {
		List<OwnerAddressInfoVO> listVOs = jdbcTemplate.query(OwnerQuery.OWNER_PROFILE_ADDRESS, (ResultSet rs) -> {
			List<OwnerAddressInfoVO> list = new ArrayList<>();
			while (rs.next()) {
				OwnerAddressInfoVO vo = new OwnerAddressInfoVO();
				vo.setOwnerId(rs.getInt("id"));
				vo.setHouseNumber(rs.getInt("houseNumber"));
				vo.setAge(rs.getInt("age"));
				vo.setDistrictName(rs.getString("districtName"));
				vo.setTalukaName(rs.getString("talukaName"));
				vo.setVillageName(rs.getString("villageName"));
				vo.setCaste(rs.getString("caste"));
				vo.setCategory(rs.getString("category"));
				vo.setParkingAreaId(rs.getInt("parkingAreaId"));
				list.add(vo);
			} // while
			return list;
		}// extractData(-)
				, userId);
		return listVOs;
	}

	public List<OwnerWalletVO> ownerWallet(int ownerId) {
		List<OwnerWalletVO> listVOs = jdbcTemplate.query(OwnerQuery.Owner_Wallet, (ResultSet rs) -> {
			List<OwnerWalletVO> list = new ArrayList<>();
			while (rs.next()) {
				OwnerWalletVO vo = new OwnerWalletVO();
				vo.setOwnerId(rs.getInt("id"));
				vo.setAmount(rs.getDouble("amount"));

				list.add(vo);
			} // while
			return list;
		}// extractData(-)
				, ownerId);
		return listVOs;
	}
}
