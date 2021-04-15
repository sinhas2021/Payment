package com.secretesc.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.exception.InvalidTransferException;
import com.secretesc.demo.model.PaymentRequest;
import com.secretesc.demo.util.ValidationUtil;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private ITransactionService transactionService;

	@Transactional
	@Override
	public String makePayment(PaymentRequest request) throws InvalidTransferException {
		Optional<Account> fromAccount = accountService.findAccountById(request.getFromAccount());
		ValidationUtil.validateTransferAmount(request.getAmount(), fromAccount.get().getBalance());
		Optional<Account> toAccount = accountService.findAccountById(request.getToAccount());
		Account toAcc = toAccount.get();
		toAcc.setBalance(toAcc.getBalance().add(request.getAmount()));
		Account fromAcc = fromAccount.get();
		fromAcc.setBalance(fromAcc.getBalance().subtract(request.getAmount()));
		accountService.saveAccount(fromAcc);
		accountService.saveAccount(toAcc);
		Integer successfulTransId = transactionService.saveTransaction(request);
		if (successfulTransId != null) {
			return "REF_" + successfulTransId;
		}
		return "Transaction was unsuccessful";// Need to be moved into properties file
	}

}
