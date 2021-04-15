package com.secretesc.demo.service;

import com.secretesc.demo.exception.InvalidTransferException;
import com.secretesc.demo.model.PaymentRequest;

public interface IPaymentService {

	String makePayment(PaymentRequest request) throws InvalidTransferException;
}
