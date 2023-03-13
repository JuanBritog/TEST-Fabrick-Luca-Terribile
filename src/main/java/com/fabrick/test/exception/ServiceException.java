package com.fabrick.test.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author JuanBrito
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 4442125330868704757L;

	private String code;
	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private HashMap<String, String> errors;


	/**
	 * @param code
	 * @param message
	 */
	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
		this.timestamp = LocalDateTime.now();

	}
	
	public ServiceException(String code, String message, HashMap<String,String> errors) {
		super(message);
		this.code = code;
		this.timestamp = LocalDateTime.now();
		this.errors = errors;

	}
	
	public String getCode() {
		return code;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public String getMessage() {
		return message;
	}
	
	

}