package com.secretesc.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.secretesc.demo.model.NotificationResponse;
import com.secretesc.demo.model.Status;

@ControllerAdvice
public class RestEntityResponseExceptionHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(RestEntityResponseExceptionHandler.class);

	@ExceptionHandler(value = { InvalidTransferException.class })
	public ResponseEntity<NotificationResponse> handleException(InvalidTransferException ex, WebRequest request) {
		NotificationResponse notificationResponse = new NotificationResponse();
		notificationResponse.setStatus(Status.FAILURE);
		LOGGER.error("Error occurred, msg " + ex.getMessage());
		notificationResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<NotificationResponse>(notificationResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<NotificationResponse> handleException(RuntimeException ex, WebRequest request) {
		NotificationResponse notificationResponse = new NotificationResponse();
		notificationResponse.setStatus(Status.FAILURE);
		LOGGER.error("Error occurred, msg " + ex.getMessage());
		ex.printStackTrace();
		notificationResponse.setErrorMessage("Error Occurred, the request cannot be processed");
		return new ResponseEntity<NotificationResponse>(notificationResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
