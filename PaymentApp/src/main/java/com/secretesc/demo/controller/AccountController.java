package com.secretesc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretesc.demo.model.AccountDTO;
import com.secretesc.demo.service.IAccountService;
/**
 * Rest Controller class representing Account entity related CRUD operations
 * @author Sourav
 *
 */
@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountDTO>> findAllAccounts() {
		List<AccountDTO> accounts = accountService.findAllAccounts();
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
	}

}
