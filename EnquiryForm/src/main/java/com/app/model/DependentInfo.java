package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DependentInfo {
@Id
private int dependentInfoId; 
private int noOfFamilyMember;
private int noOfChild;
private String maritalStatus;
private String dependentMember;
private double familyIncome;

}
