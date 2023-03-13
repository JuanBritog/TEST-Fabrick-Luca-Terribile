package com.fabrick.test.service;

import org.springframework.http.ResponseEntity;

import com.fabrick.test.request.MoneyTransferRequest;
import com.fabrick.test.response.BalanceResponse;
import com.fabrick.test.response.MoneyTransferResponse;
import com.fabrick.test.response.TransactionResponse;

public interface IAccountService {
	
	public ResponseEntity<BalanceResponse> balanceReading(String authSchema, String apiKey,
											String accountId);
	
	public ResponseEntity<TransactionResponse> accountTrx(String authSchema, String apiKey,
												String accountId, String fromAccountingDate,
												String toAccountingDate);
	
	public ResponseEntity<MoneyTransferResponse> moneyTransfer(String authSchema, String apiKey,
			String accountId, MoneyTransferRequest body);

}
