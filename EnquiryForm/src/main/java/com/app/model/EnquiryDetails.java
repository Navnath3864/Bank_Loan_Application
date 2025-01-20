package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnquiryDetails {
	@Id
	Integer customerID;
	String	firstName;
	String	lastName;
	Integer age;
	String email;
	long mobileNo;
	String pancardNo;
//	Cibil cibilSCore;
}
