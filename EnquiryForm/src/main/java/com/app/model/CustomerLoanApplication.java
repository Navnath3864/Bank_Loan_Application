package com.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CustomerLoanApplication {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private String customerDateOfBirth;
	private int customerAge;
	private int requiredTenure;
	private String customerGender;
	private String customerEmail;
	private double customerMobileNumber;
	private double customerAdditionalMobileNumber;
	private double customerAmountPaidForHome;
	private double customerTotalLoanRequired;
	private String loanStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocs allpersonalDocument;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DependentInfo familydependentInfo;

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CibilScoreData cibilScoreData;

	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails accountdetails;

	@OneToOne(cascade = CascadeType.ALL)
	private GuarantorDetails gurantordetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loandisbursement;
}
