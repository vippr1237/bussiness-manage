package com.fresher_devops.simple_bussiness.exception;

public class ReceiptNotFoundException extends RuntimeException {

	public ReceiptNotFoundException(String message) {
		super(message);
	}
}
