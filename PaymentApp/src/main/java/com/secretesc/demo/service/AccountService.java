package com.secretesc.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.model.AccountDTO;
import com.secretesc.demo.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountDTO> findAllAccounts() {
		List<AccountDTO> accounts = new ArrayList<AccountDTO>();
		Iterable<Account> accountEntities = accountRepository.findAll();
		accountEntities.forEach(x -> {
			AccountDTO accDto = new AccountDTO();
			accDto.setBalance(x.getBalance());
			accDto.setEmailaddress(x.getEmailaddress());
			accDto.setName(x.getName());
			accDto.setAccountNumber(x.getAccountNumber());
			accounts.add(accDto);
		});
		return accounts;
	}

	@Override
	public Optional<Account> findAccountById(int accountNumber) {
		return accountRepository.findById(accountNumber);
	}

	@Override
	public Account saveAccount(Account accountToBeSaved) {
		return accountRepository.save(accountToBeSaved);
	}

	@Override
	public String findEmailByAccountId(Integer accountNumber) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		return account.get().getEmailaddress();
	}

}
