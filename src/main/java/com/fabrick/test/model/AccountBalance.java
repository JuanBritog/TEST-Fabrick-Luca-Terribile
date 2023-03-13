package com.fabrick.test.model;

import lombok.Data;

@Data
public class AccountBalance{
	
	private String date;
	private double balance;
	private double availableBalance;
	private String currency;

}
