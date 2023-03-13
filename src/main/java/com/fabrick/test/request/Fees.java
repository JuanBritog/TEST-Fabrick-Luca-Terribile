package com.fabrick.test.request;

import lombok.Data;

@Data
public class Fees {
	
	private String feeCode;
	private String description;
	private double amount;
	private String currency;
	
}
