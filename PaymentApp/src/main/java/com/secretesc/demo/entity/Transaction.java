package com.secretesc.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trans_id")
	private Integer transId;
	@Column(name = "from_account")
	@NotNull
	private int fromAccount;
	@Column(name = "to_account")
	@NotNull
	private int toAccount;
	@Column
	@NotNull
	private BigDecimal amount;
	@Column
	private String remarks;
	@Column
	@NotNull
	private String status;
	@Column(name = "transaction_time")
	private LocalDateTime transTimestamp;

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTransTimestamp() {
		return transTimestamp;
	}

	public void setTransTimestamp(LocalDateTime transTimestamp) {
		this.transTimestamp = transTimestamp;
	}
}
