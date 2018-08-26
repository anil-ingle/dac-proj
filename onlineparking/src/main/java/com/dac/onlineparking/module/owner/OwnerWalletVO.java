package com.dac.onlineparking.module.owner;

public class OwnerWalletVO {
	private int ownerId;
	private double amount;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OwnerWalletVO [ownerId=" + ownerId + ", amount=" + amount + "]";
	}

}
