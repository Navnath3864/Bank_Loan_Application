package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class AccountDetails {
@Id
private int accountId;
private String accounType;

@Min(value = 0, message = "Account balance cannot be negative")
private double accountBalance;
private String accountHolderName;
private String accountStatus;

@Min(value = 1000000000, message = "Account number must have at least 10 digits")
@Column(unique = true, nullable = false)
private  long accountNumber;


}
