package com.secretesc.demo.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PaymentRequest {

	@NotNull
	private int fromAccount;
	@NotNull
	private int toAccount;
	@NotNull
	private BigDecimal amount;
	private String remarks;

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
