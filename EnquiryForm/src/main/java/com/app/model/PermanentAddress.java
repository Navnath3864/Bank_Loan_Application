package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PermanentAddress {
	@Id
private int permanentAddressId;
private String areaname;
private String cityname;
private String district;
private String state;
private	long pincode;
private	int houseNumber;
private	String streetName;

}
