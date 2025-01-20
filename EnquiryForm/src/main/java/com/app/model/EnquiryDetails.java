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
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerID;
	private String	firstName;
	private String	lastName;
	private int age;
	private String email;
	private long mobileNo;
	private String pancardNo;
	
=======
	int customerID;
	String firstName;
	String lastName;
	int age;
	String email;
	long mobileNo;
	String pancardNo;

	
//	Cibil cibilSCore;
>>>>>>> branch 'main' of https://github.com/Navnath3864/Bank_Loan_Application.git
}
