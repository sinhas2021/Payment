package com.secretesc.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.secretesc.demo.entity.Transaction;
import com.secretesc.demo.model.PaymentRequest;
import com.secretesc.demo.model.Status;
import com.secretesc.demo.model.TransactionDTO;
import com.secretesc.demo.repository.TransactionRepository;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<TransactionDTO> findTransactionsByAccountNumber(int accountNumber) {
		List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
		List<Transaction> transactionEntities = transactionRepository.findAll(createSearchByAccountSpec(accountNumber));
		transactionEntities.forEach(x -> {
			TransactionDTO transactionDto = new TransactionDTO();
			transactionDto.setAmount(x.getAmount());
			transactionDto.setFromAccount(x.getFromAccount());
			transactionDto.setRemarks(x.getRemarks());
			transactionDto.setStatus(x.getStatus());
			transactionDto.setToAccount(x.getToAccount());
			transactions.add(transactionDto);
		});
		return transactions;
	}

	private Specification<Transaction> createSearchByAccountSpec(int accountNumber) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get("fromAccount"), accountNumber),
				criteriaBuilder.equal(root.get("toAccount"), accountNumber));
	}

	@Override
	public Integer saveTransaction(PaymentRequest request) {
		Transaction transaction = new Transaction();
		transaction.setAmount(request.getAmount());
		transaction.setFromAccount(request.getFromAccount());
		transaction.setRemarks(request.getRemarks());
		transaction.setStatus(Status.SUCCESS.name());
		transaction.setToAccount(request.getToAccount());
		transaction.setTransTimestamp(LocalDateTime.now());
		Transaction savedTransaction = transactionRepository.save(transaction);
		return savedTransaction.getTransId();
	}

}
