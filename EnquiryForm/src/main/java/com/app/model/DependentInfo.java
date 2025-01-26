package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class DependentInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dependentInfoId; 
	
	@NotNull(message = "Number of family members is required")
	private int noOfFamilyMember;
	
	@NotNull(message = "Number of children is required")
	private int noOfChild;
	
	@NotBlank(message = "Marital status is required")
	@Pattern(regexp = "^(Single|Married|Divorced)$", message = "Marital status must be 'Single', 'Married', or 'Divorced'")
	private String maritalStatus;
	
	@NotBlank(message = "Dependent member is required")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "dependentMember must contain only alphabetic characters and spaces")
	private String dependentMember;
	
	@Min(value = 10000, message = "Family income must be greater than 10000")
	private double familyIncome;

}

