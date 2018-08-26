package com.dac.onlineparking.module.user;

public class WalletMoneyVO {
	private int walletId;
	private double totalAmount;

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "WalletMoney [walletId=" + walletId + ", totalAmount=" + totalAmount + "]";
	}
}
