package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountDetails {
@Id
private int accountId;
private String accounType;
private double accountBalance;
private String accountHolderName;
private String accountStatus;
private  long accountNumber;

}
