package com.fabrick.test.response;

import java.util.List;

import com.fabrick.test.request.Amount;
import com.fabrick.test.request.Creditor;
import com.fabrick.test.request.Debtor;
import com.fabrick.test.request.Fees;

import lombok.Data;

@Data
public class Transfer {
	
	private String moneyTransferId;
	private String status;
	private String direction;
	private Creditor creditor;
	private Debtor debtor;
	private String cro;
	private String uri;
	private String trn;
	private String description;
	private String createdDatetime;
	private String accountedDatetime;
	private String debtorValueDate;
	private String creditorValueDate;
	private Amount amount;
	private boolean isUrgent;
	private boolean isInstant;
	private String feeType;
	private String feeAccountId;
	private List<Fees> fees;
	private boolean hasTaxRelief;

}
