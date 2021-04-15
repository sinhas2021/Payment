package com.secretesc.demo.service;

import java.util.List;
import java.util.Optional;

import com.secretesc.demo.entity.Account;
import com.secretesc.demo.model.AccountDTO;

public interface IAccountService {

	List<AccountDTO> findAllAccounts();

	Account saveAccount(Account accountToBeSaved);

	Optional<Account> findAccountById(int accountNumber);

	String findEmailByAccountId(Integer fromAccountNo);
}
