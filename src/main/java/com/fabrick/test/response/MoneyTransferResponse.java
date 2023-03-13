package com.fabrick.test.response;

public class MoneyTransferResponse extends RestResponse{
	
	private Transfer transfer;

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	
}
