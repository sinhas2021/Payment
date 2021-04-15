package com.secretesc.demo.util;

import java.math.BigDecimal;

import com.secretesc.demo.exception.InvalidTransferException;

public final class ValidationUtil {

	public static void validateTransferAmount(BigDecimal amount, BigDecimal balance) throws InvalidTransferException {
		if (amount == null || balance == null || amount.compareTo(balance) > 1) {
			throw new InvalidTransferException("This request could not be processed, please check the request");
		}
	}

}
