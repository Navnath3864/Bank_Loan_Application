
package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class GuarantorDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int guarantorId;
//	
//	@NotBlank(message = "GuarantorName is required")
//	@Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "GuarantorName must start with a capital letter and contain only alphabets")
	private String guarantorName;
	
//	@NotBlank(message = "GuarantorDateOfBirth is required")
//	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "GuarantorDateOfBirth must be in the format yyyy-MM-dd")
	private String guarantorDateOfBirth;
	
//	@NotBlank(message = "Guarantor's relationship with customer is required")
//	@Pattern(regexp = "^(Friend|Colleague|Family Member|Other)$", message = "Guarantor relationship must be 'Friend', 'Colleague', 'Family Member', or 'Other'")
	private String guarantorRelationshipwithCustomer;
	
//	@Min(value = 1000000000, message = "Mobile number must be at least 10 digits")
//	@Max(value = 9999999999L, message = "Mobile number must be at most 10 digits")
	private long guarantorMobileNumber;
	
//	@Min(value = 100000000000L, message = "Aadhaar card number must be a 12-digit number")
//	@Max(value = 999999999999L, message = "Aadhaar card number must be a 12-digit number")
	private long guarantorAdharCardNo;
	
//	@NotBlank(message = "Guarantor mortgage details are required")
	private String guarantorMortgageDetails;
	
//	@NotBlank(message = "Guarantor job details are required")
//	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "guarantorJobDetails must contain only alphabetic characters and spaces")
	private String guarantorJobDetails;
	
//	@NotBlank(message = "Guarantor local address is required")
//	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "guarantorLocalAddress must contain only alphabetic characters and spaces")
	private String guarantorLocalAddress;
	
//	@NotBlank(message = "Guarantor permant address is required")
//	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "guarantorPermanentAddress must contain only alphabetic characters and spaces")
	private String guarantorPermanentAddress;

}