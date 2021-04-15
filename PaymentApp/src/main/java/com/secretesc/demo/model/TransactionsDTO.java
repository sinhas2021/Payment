package com.secretesc.demo.model;

import java.util.List;

public class TransactionsDTO {

	private List<TransactionDTO> transactions;

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
}
