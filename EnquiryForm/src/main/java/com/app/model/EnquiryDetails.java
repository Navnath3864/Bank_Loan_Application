package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnquiryDetails {
	@Id
	int customerID;
	String firstName;
	String lastName;
	int age;
	String email;
	long mobileNo;
	String pancardNo;

	
//	Cibil cibilSCore;
}
