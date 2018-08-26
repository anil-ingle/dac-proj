package com.dac.onlineparking.module.owner;

public class OwnerAddressInfoVO {
	private int ownerId;
	private int houseNumber;
	private int age;
	private String districtName;
	private String talukaName;
	private String villageName;
	private String caste;
	private String category;
	private int parkingAreaId;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getParkingAreaId() {
		return parkingAreaId;
	}

	public void setParkingAreaId(int parkingAreaId) {
		this.parkingAreaId = parkingAreaId;
	}

	@Override
	public String toString() {
		return "OwnerAddressInfoVO [ownerId=" + ownerId + ", houseNumber=" + houseNumber + ", age=" + age
				+ ", districtName=" + districtName + ", talukaName=" + talukaName + ", villageName=" + villageName
				+ ", caste=" + caste + ", category=" + category + ", parkingAreaId=" + parkingAreaId + "]";
	}

}
