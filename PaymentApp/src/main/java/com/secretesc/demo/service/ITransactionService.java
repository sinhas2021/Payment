package com.secretesc.demo.service;

import java.util.List;

import com.secretesc.demo.model.PaymentRequest;
import com.secretesc.demo.model.TransactionDTO;

public interface ITransactionService {

	List<TransactionDTO> findTransactionsByAccountNumber(int accountNumber);

	Integer saveTransaction(PaymentRequest request);

}
