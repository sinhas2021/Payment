package com.secretesc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secretesc.demo.model.TransactionDTO;
import com.secretesc.demo.service.ITransactionService;
/**
 * Rest Controller class representing Transaction entity related CRUD operations
 * @author Sourav
 *
 */
@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private ITransactionService accountService;

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionDTO>> findTransactionsByAccountNumber(@RequestParam int accountNumber) {
		List<TransactionDTO> transactions = accountService.findTransactionsByAccountNumber(accountNumber);
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}
}
