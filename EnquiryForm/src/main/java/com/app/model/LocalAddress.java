package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class LocalAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int localAddressId;
	
	@NotBlank(message = "Areaname is required")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Areaname must contain only alphabetic characters and spaces")
	private String areaName;
	
	@NotBlank(message = "Cityname is required")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Cityname must contain only alphabetic characters and spaces")
	private String cityName;
	
	@NotBlank(message = "District are required")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "District must contain only alphabetic characters and spaces")
	private String district;
	
	@NotBlank(message = "State are required")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "State must contain only alphabetic characters and spaces")
	private String state;
	
	
	@Min(value = 100000, message = "Pincode must be a 6-digit number and not less than 100000")
    @Max(value = 999999, message = "Pincode must be a 6-digit number and not more than 999999")
	private long pincode;
	
	@NotNull(message = "House number is required")
	@Min(value = 1, message = "House number must be greater than 0")
	private int houseNumber;
	
	@NotBlank(message = "StreetName are required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Street name can only contain letters, numbers, and spaces")
	private String streetName;

}