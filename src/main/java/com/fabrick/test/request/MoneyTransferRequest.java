package com.fabrick.test.request;

import lombok.Data;

@Data
public class MoneyTransferRequest {
	
	private Creditor creditor;
	private String executionDate;
	private String uri;
	private String description;
	private int amount;
	private String currency;
	private Boolean isUrgent;
	private Boolean isInstant;
	private String feeType;
	private String feeAccountId;
	private TaxRelief taxRelief;

}