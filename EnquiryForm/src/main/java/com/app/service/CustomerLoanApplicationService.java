package com.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.app.model.CustomerLoanApplication;

public interface CustomerLoanApplicationService {

	 

	public CustomerLoanApplication saveDetails(String customerLoanApplication, int id, MultipartFile addressProof,
			MultipartFile panCard, MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo,
			MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips);
}
