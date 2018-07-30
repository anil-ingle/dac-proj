package com.dac.onlineparking.module.user;

public class WolletStatusVO {
	private int ownerId;
	private Double amount;
	private boolean isSuccess;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "WolletStatusVO [ownerId=" + ownerId + ", amount=" + amount + ", isSuccess=" + isSuccess + "]";
	}

}