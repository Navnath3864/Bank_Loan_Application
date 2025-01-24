package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LocalAddress {
@Id
private int localAddressId;
private String areaname;
private String cityname;
private String district;
private String state;
private long pincode;
private int houseNumber;
private String streetName;

}
