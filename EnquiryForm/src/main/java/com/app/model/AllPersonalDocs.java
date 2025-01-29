package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class AllPersonalDocs {
	@Id
	private int documentID ;
	@Lob
	@NotNull(message = "Address proof is required")
	@Column(length=999999999)
	private byte[] addressProof;
	
	@Lob
	@NotNull(message = "PAN card is required")
	@Column(length=999999999)
	private byte[]panCard;
	
	@Lob
	@NotNull(message = "Aadhar card is required")
	@Column(length=999999999)
	private byte[] addharCard;

	@Lob
	@Column(length=999999999)
	private byte[] incomeTax;

	@Lob
	@NotNull(message = "Photo is required")
	@Column(length=999999999)
	private byte[] photo;
	
	
	@Lob
	@NotNull(message = "Signature is required")
	@Column(length=999999999)
	private byte[] signature;
	
	
	@Lob
	@NotNull(message = "Bank Check is required")
	@Column(length=999999999)
	private byte[] bankCheque;
	
	@Lob
	@NotNull(message = "Salary Slip is required")
	@Column(length=999999999)
	private byte[] salarySlips;

}
