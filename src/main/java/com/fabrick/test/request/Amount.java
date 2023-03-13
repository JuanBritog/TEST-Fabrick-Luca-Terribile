package com.fabrick.test.request;

import lombok.Data;

@Data
public class Amount {
	
	private int debtorAmount;
	private String debtorCurrency;
	private int creditorAmount;
	private String creditorCurrency;
	private String creditorCurrencyDate;
	private int exchangeRate;

}
