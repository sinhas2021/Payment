package com.secretesc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretesc.demo.exception.InvalidTransferException;
import com.secretesc.demo.model.PaymentRequest;
import com.secretesc.demo.model.PaymentResponse;
import com.secretesc.demo.service.IEmailService;
import com.secretesc.demo.service.IPaymentService;

/**
 * Rest Controller class representing Payment related operations. The class is
 * designed to perform orchestration between account and transaction entity
 * related operations
 * 
 * @author Sourav
 *
 */
@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IEmailService emailService;

	@PostMapping("/payment")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) throws InvalidTransferException {
		final String transactionRefNo = paymentService.makePayment(request);
		PaymentResponse response = new PaymentResponse();
		response.setTransactionRefNo(transactionRefNo);
		emailService.sendEmail(request.getFromAccount(), request.getToAccount());
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
}
