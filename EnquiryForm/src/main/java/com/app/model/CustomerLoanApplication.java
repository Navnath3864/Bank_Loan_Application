package com.app.model;

import com.app.model.CibilScoreData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import lombok.Data;


@Entity
@Data
public class CustomerLoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerLoanID;

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
	@Valid
	private AllPersonalDocs allPersonalDocument;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private DependentInfo familyDependentInfo;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private CustomerAddress customerAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
    private CibilScoreData cibilScoreData;
    
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private AccountDetails accountDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private GuarantorDetails gurantorDetails;

	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loandisbursement;

	@OneToOne(cascade = CascadeType.ALL)
	private Ledger ledger;

	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;

	@OneToOne
	private CustomerVerification customerVerification;

}
