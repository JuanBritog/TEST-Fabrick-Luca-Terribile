package com.fabrick.test.model;

import com.fabrick.test.response.Transfer;

import lombok.Data;

@Data
public class PayloadMoneyTransfer  extends Payload{
	
	private Transfer payload;
}
