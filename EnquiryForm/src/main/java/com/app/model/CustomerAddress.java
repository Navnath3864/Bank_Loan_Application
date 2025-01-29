package com.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class CustomerAddress {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int customerAddressId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private PermanentAddress permanentAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private LocalAddress localAddress;

@Id

@OneToOne(cascade = CascadeType.ALL)
@NotNull(message = "Permanent address is required")
private PermanentAddress permanentAddress;

@OneToOne(cascade = CascadeType.ALL)
@NotNull(message = "Local address is required")
private LocalAddress localAddress;


@NotBlank(message = "Street name is required")
private String street;

@NotBlank(message = "City name is required")
private String city;

}
