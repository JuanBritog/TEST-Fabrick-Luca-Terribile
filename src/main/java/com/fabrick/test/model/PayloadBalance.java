package com.fabrick.test.model;

import lombok.Data;

@Data
public class PayloadBalance extends Payload{
	
	private AccountBalance payload;

}
