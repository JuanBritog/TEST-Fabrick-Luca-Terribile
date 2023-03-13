package com.fabrick.test.model;

import lombok.Data;

@Data
public class AccountTransaction {
	
	private String transactionId;
	private String operationId;
	private String accountingDate;
	private String valueDate;
    private Type type;
    private int amount;
    private String currency;
    private String description;

}
