package com.fabrick.test.model;

import java.util.List;

import lombok.Data;

@Data
public class ListPayload {
	
	private List<AccountTransaction> list;

}
