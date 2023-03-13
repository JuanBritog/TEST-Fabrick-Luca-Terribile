package com.fabrick.test.request;

import lombok.Data;

@Data
public class Creditor {
	
	private String name;
	private Account account;
	private Address address;

}
