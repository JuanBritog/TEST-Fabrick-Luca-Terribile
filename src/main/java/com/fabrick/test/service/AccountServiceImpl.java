package com.fabrick.test.service;

import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fabrick.test.exception.ServiceException;
import com.fabrick.test.model.LogsRegistre;
import com.fabrick.test.model.PayloadBalance;
import com.fabrick.test.model.PayloadMoneyTransfer;
import com.fabrick.test.model.PayloadTransaction;
import com.fabrick.test.request.MoneyTransferRequest;
import com.fabrick.test.response.BalanceResponse;
import com.fabrick.test.response.MoneyTransferResponse;
import com.fabrick.test.response.TransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountServiceImpl implements IAccountService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate clientRest;
	
	@Autowired
	private ILogsService logsService;
	
	@Value("${sping.external.service.base-url}")
    private String basePath;

	/**
	 * connect the api rest with the external balance reading
	 */
	public ResponseEntity<BalanceResponse> balanceReading(String authSchema, String apiKey, 
			String accountId) {
		
		if (null == accountId) {
			LOGGER.error("Bad request : The account ID cannot be null.");
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"The account ID cannot be null.");
		}
		
		BalanceResponse balanceResponse = new BalanceResponse();
		
		try {
			LOGGER.info("Initializing AccountServiceImpl.balanceReading()");
			LOGGER.info("headers, Auth-Schema: "+authSchema+" ,Api-Key: "+apiKey);
			LOGGER.info("AccountId: "+accountId);
			
			String endpoint = this.basePath+accountId+"/balance";
			
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Auth-Schema", authSchema);
			headers.set("Api-Key", apiKey);

			HttpEntity<PayloadBalance> entity = new HttpEntity<>(headers);
			
			ResponseEntity<PayloadBalance> response = clientRest.exchange(
					endpoint, HttpMethod.GET, entity,
					new ParameterizedTypeReference<PayloadBalance>() {
					});
			
			//save the registre in the logs
			LogsRegistre logs = new LogsRegistre();
			logs.setOperation("GET Account Balance ");
			logs.setDescription("get account balance for client: "+accountId);
			
			logsService.save(logs);
			// save the registre in the logs
			
			balanceResponse.setAccountBalance(response.getBody().getPayload());
			balanceResponse.addMetadata("Response ok", response.getBody().getStatus(), "Response successfull");
			
			LOGGER.info("response GET account balance: ", response);
						
			return new ResponseEntity<BalanceResponse>(balanceResponse, HttpStatus.OK);
			
		} catch (ServiceException e) {

			throw e;

		} catch (Exception e) {
					
			String [] arr = e.getMessage().split("<EOL><EOL>");
			String [] codeApi = arr[4].split("\\:");
			String codeClean = codeApi[1].replace("\"", "");
			
			balanceResponse.addMetadata("ko", codeClean, "api error");
			
			return new ResponseEntity<BalanceResponse>(balanceResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			//throw new ServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		}
		
		
	}

	/**
	 * connect the api rest with the account Transaction
	 */
	public ResponseEntity<TransactionResponse> accountTrx(String authSchema, String apiKey, String accountId,
			String fromAccountingDate, String toAccountingDate) {
		
		if (null == accountId) {
			LOGGER.error("Bad request : The account ID cannot be null.");
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"The account ID cannot be null.");
		}
		
		TransactionResponse transactionResponse = new TransactionResponse();
		
		try {
			LOGGER.info("Initializing AccountServiceImpl.accountTrx()");
			LOGGER.info("headers, Auth-Schema: "+authSchema+" ,Api-Key: "+apiKey);
			LOGGER.info("AccountId: "+accountId);
			LOGGER.info("fromAccountingDate: "+fromAccountingDate);
			LOGGER.info("toAccountingDate: "+toAccountingDate);
			
			
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Auth-Schema", authSchema);
			headers.set("Api-Key", apiKey);

			HttpEntity<PayloadTransaction> entity = new HttpEntity<>(headers);
			
			ResponseEntity<PayloadTransaction> response = clientRest.exchange(
					this.basePath+"{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}", 
					HttpMethod.GET, 
					entity,
					new ParameterizedTypeReference<PayloadTransaction>() {},
					accountId,
					fromAccountingDate,
					toAccountingDate
					);
			
			//save the registre in the logs
			LogsRegistre logs = new LogsRegistre();
			logs.setOperation("GET Account Transactions ");
			logs.setDescription("get account Transactions for client: "+accountId);
			
			logsService.save(logs);
			// save the registre in the logs
			
			transactionResponse.setAccountTransaction(response.getBody().getPayload());
			transactionResponse.addMetadata("Response ok", response.getBody().getStatus(), "Response successfull");
			
			LOGGER.info("response GET account transaction: ", response);
						
			return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.OK);
			
		} catch (ServiceException e) {

			throw e;

		} catch (Exception e) {
					
			String [] arr = e.getMessage().split("<EOL><EOL>");
			String [] codeApi = arr[4].split("\\:");
			String codeClean = codeApi[1].replace("\"", "");
			
			transactionResponse.addMetadata("ko", codeClean, "api error");
			
			return new ResponseEntity<TransactionResponse>(transactionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			//throw new ServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		}
	}

	/**
	 * connect the api rest with the money trasnfer
	 */
	public ResponseEntity<MoneyTransferResponse> moneyTransfer(String authSchema, String apiKey, String accountId,
			MoneyTransferRequest body) {
		if (null == accountId) {
			LOGGER.error("Bad request : The account ID cannot be null.");
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"The account ID cannot be null.");
		}
		
		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		
		try {
			
			LOGGER.info("Initializing AccountServiceImpl.moneyTransfer()");
			LOGGER.info("headers, Auth-Schema: "+authSchema+" ,Api-Key: "+apiKey);
			LOGGER.info("AccountId: "+accountId);
			LOGGER.info("body: "+body.toString());
			
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Auth-Schema", authSchema);
			headers.set("Api-Key", apiKey);

			HttpEntity<MoneyTransferRequest> entity = new HttpEntity<>(body , headers);
			
			ResponseEntity<PayloadMoneyTransfer> response = clientRest.exchange(
					this.basePath+accountId+"/payments/money-transfers", 
					HttpMethod.POST, 
					entity,
					PayloadMoneyTransfer.class
					);
			
			//save the registre in the logs
			LogsRegistre logs = new LogsRegistre();
			logs.setOperation("Create Money Transfer ");
			logs.setDescription("transfer money for client: "+accountId);
			
			logsService.save(logs);
			// save the registre in the logs
			
			moneyTransferResponse.setTransfer(response.getBody().getPayload());
			moneyTransferResponse.addMetadata("Response ok", "00", "Response successfull");
					
			return new ResponseEntity<MoneyTransferResponse>(moneyTransferResponse, HttpStatus.OK);
			
		} catch (ServiceException e) {

			throw e;

		} catch (Exception e) {
			String [] arr = e.getMessage().split("<EOL><EOL>");
			String [] codeApi = arr[4].split("\\:");
			String codeClean = codeApi[1].replace("\"", "");
			
			moneyTransferResponse.addMetadata("ko", codeClean, "api error");
			
			return new ResponseEntity<MoneyTransferResponse>(moneyTransferResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			//throw new ServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), errors);
		}
	}

	
	
	
}
