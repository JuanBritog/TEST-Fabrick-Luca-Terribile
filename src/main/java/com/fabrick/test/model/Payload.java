package com.fabrick.test.model;

import java.util.List;

import lombok.Data;

@Data
public class Payload {
	
	private String status;
	private List<Errors> errors;

}
