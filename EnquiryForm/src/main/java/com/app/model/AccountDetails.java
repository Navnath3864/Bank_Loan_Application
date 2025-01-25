
package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class AccountDetails {
     
	@Id
    private int accountId;

	@NotBlank(message = "Account Type is required")
	@Pattern(regexp = "^(Savings|Current|Fixed Deposit)$", message = "Account Type must be 'Savings', 'Current', or 'Fixed Deposit'")
    private String accounType;
	
	@DecimalMin(value = "0.0", inclusive = true, message = "Account balance must be non-negative")
    private double accountBalance;
	
	@NotBlank(message = "AccountHolderName is required")
	@Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "AccountHolderName must start with a capital letter and contain only alphabets")
    private String accountHolderName;
	
	@NotBlank(message = "Account status is required")
	@Pattern(regexp = "^(Active|Inactive|Closed)$", message = "Account status must be 'Active', 'Inactive', or 'Closed'")
    private String accountStatus;
	
	@Min(value = 1000000000L, message = "Account number must be at least 10 digits")
	@Max(value = 9999999999L, message = "Account number cannot exceed 10 digits")
    private  long accountNumber;

}
