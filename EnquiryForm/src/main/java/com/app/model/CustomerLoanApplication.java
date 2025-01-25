package com.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class CustomerLoanApplication {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerID;
	
	@NotBlank(message = "CustomerName is required")
	@Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "CustomerName must start with a capital letter and contain only alphabets")
	private String customerName;
	
	@NotBlank(message = "CustomerDateOfBirthis required")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "CustomerDateOfBirth must be in the format yyyy-MM-dd")
	private String customerDateOfBirth;
	
	@Min(value = 18, message = "Rquired Age at Least 18 Years")
	@NotNull(message = "Age is required to fill")
	private int customerAge;
	
	@NotNull(message = "Tenure is required")
	@Min(value = 1, message = "Tenure must be at least 1 year")
	@Max(value = 50, message = "Tenure cannot exceed 50 years")
	private int requiredTenure;
	
	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be 'Male', 'Female', or 'Other'")
	private String customerGender;
	
	@Email(message = "Invalid Email Id")
	private String customerEmail;
	
	@Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
	@Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
	private double customerMobileNumber;
	
	@Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
	@Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
	private double customerAdditionalMobileNumber;
	
	@Min(value = 1, message = "Amount must be at least 1")
	@DecimalMax(value = "1000000000.00", message = "Amount cannot exceed 1 billion")
	private double customerAmountPaidForHome;
	
	@DecimalMin(value = "0.0", inclusive = true, message = "Loan amount must be non-negative")
	@DecimalMax(value = "1000000000.0", inclusive = true, message = "Loan amount cannot exceed 1 billion")
	private double customerTotalLoanRequired;
	
	@NotBlank(message = "Loan status is required")
	@Pattern(regexp = "^(Submit|Verified|Sanctioned|Disbursed)$", message = "Loan status must be one of the following: Submit, Verified, Sanctioned, Disbursed")
	private String loanStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocs allPersonalDocument;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DependentInfo familyDependentInfo;

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CibilScoreData cibilScoreData;

	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails accountDetails;

	@OneToOne(cascade = CascadeType.ALL)
	private GuarantorDetails gurantorDetails;
	
}
