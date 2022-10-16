package com.loan.exceptions;

import java.time.LocalDate;

public class ErrorResponse {
	private LocalDate time;
	private int statusCode;
    private String message;

 
    public ErrorResponse() {
		super();
	}
    
	public ErrorResponse(LocalDate time, int statusCode, String message) {
		super();
		this.time = time;
		this.statusCode = statusCode;
		this.message = message;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [time=" + time + ", statusCode=" + statusCode + ", message=" + message + "]";
	}
}
