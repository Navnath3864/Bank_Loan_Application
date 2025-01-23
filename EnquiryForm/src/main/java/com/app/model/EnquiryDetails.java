package com.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class EnquiryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerID;
	
	@NotBlank(message = "First Name is required")
	private String firstName;
	
	@NotBlank(message = "Last Name is required")
	private String lastName;
	
	private int age;
	
	@Email(message = "Invalid Email Id")
	private String email;
	
//	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
	private long mobileNo;
	
//	@NotBlank(message = "PancardNo is required")
	private String pancardNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cibil_id")
	private CibilScoreData cibilScoreData;

//	"firstName":"Harshada",
//	"lastName":"Thorat",
//	"age":25,
//	"email":"thoratharshada065@gmail.com",
//	"mobileno":7028763328,
//	"pancard":"harshada12"
}
