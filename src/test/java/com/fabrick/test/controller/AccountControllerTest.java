package com.fabrick.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fabrick.test.model.AccountBalance;
import com.fabrick.test.model.ListPayload;
import com.fabrick.test.request.MoneyTransferRequest;
import com.fabrick.test.response.BalanceResponse;
import com.fabrick.test.response.MoneyTransferResponse;
import com.fabrick.test.response.TransactionResponse;
import com.fabrick.test.response.Transfer;
import com.fabrick.test.service.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

class AccountControllerTest {
	
	private MockMvc mockMvc;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	AccountController accountCtrl;
	
	@Mock
	private IAccountService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountCtrl).build();
	}
	
	@Test
	public void getAccountBalanceTest() throws Exception {
		
		BalanceResponse balanceResponse = new BalanceResponse();
		AccountBalance accountbalance = new AccountBalance();
		
		accountbalance.setAvailableBalance(4);
		accountbalance.setBalance(4);
		accountbalance.setCurrency("EUR");
		accountbalance.setDate("2023-03-13");
		
		balanceResponse.setAccountBalance(accountbalance);
		balanceResponse.addMetadata("Response ok", "ok", "Response successfull");

		
		when(service.balanceReading("S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP", "14537780"))
				.thenReturn(new ResponseEntity<BalanceResponse>(balanceResponse, HttpStatus.OK));
		
		this.mockMvc.perform(get("/v1/account/balance/{accountId}", "14537780")
				.header("Auth-Schema", "S2S")
				.header("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.accountBalance").exists())
				.andExpect(jsonPath("$.accountBalance.currency").value("EUR"))
				.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void getAccountTransactionTest() throws Exception {
		
		TransactionResponse reponse = new TransactionResponse();
		ListPayload payLoad = new ListPayload();
		
		reponse.setAccountTransaction(payLoad);
		reponse.addMetadata("Response ok", "ok", "Response successfull");

		
		when(service.accountTrx("S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP", 
								"14537780", "2022-04-01", "2022-12-01"))
				.thenReturn(new ResponseEntity<TransactionResponse>(reponse, HttpStatus.OK));
		
		this.mockMvc.perform(get("/v1/account/balance/{accountId}/transactions/{fromAccountingDate}/{toAccountingDate}"
								, "14537780", "2022-04-01", "2022-12-01")
				.header("Auth-Schema", "S2S")
				.header("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void getMoneyTransferTest() throws Exception {
		
		MoneyTransferResponse reponse = new MoneyTransferResponse();
		Transfer transfer = new Transfer();
		MoneyTransferRequest body = new MoneyTransferRequest();
		
		reponse.setTransfer(transfer);
		reponse.addMetadata("Response ok", "ok", "Response successfull");

		
		when(service.moneyTransfer("S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP", "14537780", body))
				.thenReturn(new ResponseEntity<MoneyTransferResponse>(reponse, HttpStatus.OK));
		
		String jsonRequest = "{\n"
				+ "  \"creditor\": {\n"
				+ "    \"name\": \"John Doe\",\n"
				+ "    \"account\": {\n"
				+ "      \"accountCode\": \"IT23A0336844430152923804660\",\n"
				+ "      \"bicCode\": \"SELBIT2BXXX\"\n"
				+ "    },\n"
				+ "    \"address\": {\n"
				+ "      \"address\": null,\n"
				+ "      \"city\": null,\n"
				+ "      \"countryCode\": null\n"
				+ "    }\n"
				+ "  },\n"
				+ "  \"executionDate\": \"2019-04-01\",\n"
				+ "  \"uri\": \"REMITTANCE_INFORMATION\",\n"
				+ "  \"description\": \"Payment invoice 75/2017\",\n"
				+ "  \"amount\": 800,\n"
				+ "  \"currency\": \"EUR\",\n"
				+ "  \"isUrgent\": false,\n"
				+ "  \"isInstant\": false,\n"
				+ "  \"feeType\": \"SHA\",\n"
				+ "  \"feeAccountId\": \"45685475\",\n"
				+ "  \"taxRelief\": {\n"
				+ "    \"taxReliefId\": \"L449\",\n"
				+ "    \"isCondoUpgrade\": false,\n"
				+ "    \"creditorFiscalCode\": \"56258745832\",\n"
				+ "    \"beneficiaryType\": \"NATURAL_PERSON\",\n"
				+ "    \"naturalPersonBeneficiary\": {\n"
				+ "      \"fiscalCode1\": \"MRLFNC81L04A859L\",\n"
				+ "      \"fiscalCode2\": null,\n"
				+ "      \"fiscalCode3\": null,\n"
				+ "      \"fiscalCode4\": null,\n"
				+ "      \"fiscalCode5\": null\n"
				+ "    },\n"
				+ "    \"legalPersonBeneficiary\": {\n"
				+ "      \"fiscalCode\": null,\n"
				+ "      \"legalRepresentativeFiscalCode\": null\n"
				+ "    }\n"
				+ "  }\n"
				+ "}";
		
		
		this.mockMvc.perform(post("/v1/account/transfer/{accountId}","14537780")
				.header("Auth-Schema", "S2S")
				.header("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		
	}

}
