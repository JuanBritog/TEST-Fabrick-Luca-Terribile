package com.fabrick.test.response;

import com.fabrick.test.model.ListPayload;

public class TransactionResponse extends RestResponse{

	private ListPayload accountTransaction;

	public ListPayload getAccountTransaction() {
		return accountTransaction;
	}

	public void setAccountTransaction(ListPayload accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

	

}
