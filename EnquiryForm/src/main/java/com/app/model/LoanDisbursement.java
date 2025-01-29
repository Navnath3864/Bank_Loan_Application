package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoanDisbursement {

@Id
private int agreementId;
private int loanNo;
private String agreementDate;
private String amountPayType;
private Double totalAmount;
private String bankName;
private Long accountNumber;
private String IFSCCode;
private String accountType;
private Double transferAmount;
private String paymentStatus;
private String amountPaidDate;


	
	
}
