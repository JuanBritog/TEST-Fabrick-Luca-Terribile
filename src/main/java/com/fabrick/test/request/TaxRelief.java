package com.fabrick.test.request;

import lombok.Data;

@Data
public class TaxRelief {
	
	private String taxReliefId;
	private Boolean isCondoUpgrade;
	private String creditorFiscalCode;
	private String beneficiaryType;
	private NaturalPersonBeneficiary naturalPersonBeneficiary;
	private LegalPersonBeneficiary legalPersonBeneficiary;
	

}