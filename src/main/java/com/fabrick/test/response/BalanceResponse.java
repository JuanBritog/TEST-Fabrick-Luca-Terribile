package com.fabrick.test.response;

import com.fabrick.test.model.AccountBalance;

/**
 * 
 * @author JuanBrito
 *
 */
public class BalanceResponse extends RestResponse{
	
	private AccountBalance accountBalance;

	public AccountBalance getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(AccountBalance accountBalance) {
		this.accountBalance = accountBalance;
	}

}
