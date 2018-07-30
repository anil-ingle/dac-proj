package com.dac.onlineparking.module.user;

public class WolletBookVO {
	private Integer userId;
	private Integer areaId;
	private Double wTotal;
	private Double wBill;
	private String bookedSlots;
	private String timeTaken;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Double getwTotal() {
		return wTotal;
	}

	public void setwTotal(Double wTotal) {
		this.wTotal = wTotal;
	}

	public Double getwBill() {
		return wBill;
	}

	public void setwBill(Double wBill) {
		this.wBill = wBill;
	}

	public String getBookedSlots() {
		return bookedSlots;
	}

	public void setBookedSlots(String bookedSlots) {
		this.bookedSlots = bookedSlots;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	@Override
	public String toString() {
		return "WolletBookVO [userId=" + userId + ", areaId=" + areaId + ", wTotal=" + wTotal + ", wBill=" + wBill
				+ ", bookedSlots=" + bookedSlots + ", timeTaken=" + timeTaken + "]";
	}

}