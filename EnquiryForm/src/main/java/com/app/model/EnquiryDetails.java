package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnquiryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerID;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private long mobileNo;

	private String pancardNo;

//	"firstName":"Harshada",
//	"lastName":"Thorat",
//	"age":25,
//	"email":"thoratharshada065@gmail.com",
//	"mobileno":7028763328,
//	"pancard":"harshada12"
}
