package com.fabrick.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrick.test.request.MoneyTransferRequest;
import com.fabrick.test.response.BalanceResponse;
import com.fabrick.test.response.MoneyTransferResponse;
import com.fabrick.test.response.TransactionResponse;
import com.fabrick.test.service.IAccountService;

@RestController
@RequestMapping("/v1")
public class AccountController {
	
	@Autowired
	private IAccountService service;
	
	@GetMapping("/account/balance/{accountId}")
	public ResponseEntity<BalanceResponse> getAccountBalance(
			@RequestHeader(value = "Auth-Schema", required = true) String authSchema,
			@RequestHeader(value = "Api-Key", required = true) String apikEY,
			@PathVariable String accountId) {
		
		ResponseEntity<BalanceResponse> response = service.balanceReading(authSchema, apikEY, accountId);
		return response;
	}
	
	@GetMapping("/account/balance/{accountId}/transactions/{fromAccountingDate}/{toAccountingDate}")
	public ResponseEntity<TransactionResponse> getAccountTransaction(
			@RequestHeader(value = "Auth-Schema", required = true) String authSchema,
			@RequestHeader(value = "Api-Key", required = true) String apikEY,
			@PathVariable String accountId,
			@PathVariable String fromAccountingDate,
			@PathVariable String toAccountingDate ) {
		
		ResponseEntity<TransactionResponse> response = service.accountTrx(authSchema, apikEY, 
									accountId, fromAccountingDate, toAccountingDate);
		return response;
	}
	
	@PostMapping("/account/transfer/{accountId}")
	public ResponseEntity<MoneyTransferResponse> getMoneyTransfer(
			@RequestHeader(value = "Auth-Schema", required = true) String authSchema,
			@RequestHeader(value = "Api-Key", required = true) String apikEY,
			@PathVariable String accountId,
			@RequestBody MoneyTransferRequest body ) {
		
		ResponseEntity<MoneyTransferResponse> response = service.moneyTransfer(authSchema, 
																		apikEY, accountId, body);
		return response;
	}

}
