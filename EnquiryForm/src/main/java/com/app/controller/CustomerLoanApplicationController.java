package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.model.AllPersonalDocs;
import com.app.model.CustomerLoanApplication;
import com.app.service.CustomerLoanApplicationService;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/app")
public class CustomerLoanApplicationController {

	@Autowired

	
	

	CustomerLoanApplicationService customerLoanApplicationService;


	@PostMapping("/api/customerloanapplication/{id}")


//	public ResponseEntity<CustomerLoanApplication> saveDetails(@Valid @RequestBody CustomerLoanApplication customerLoanApplication,@PathVariable int id){

	public ResponseEntity<CustomerLoanApplication> saveDetails(@RequestPart("data")  String customerLoanApplication,
			@RequestPart("addressProof") MultipartFile addressProof,
			@RequestPart("panCard") MultipartFile panCard,
			@RequestPart("incomeTax") MultipartFile incomeTax,
			@RequestPart("addharCard") MultipartFile addharCard,
			@RequestPart("photo") MultipartFile photo,
			@RequestPart("signature") MultipartFile signature,
			@RequestPart("bankCheque") MultipartFile bankCheque,
			@RequestPart("salarySlips") MultipartFile salarySlips,
			@PathVariable int id) {

		CustomerLoanApplication details = customerLoanApplicationService.saveDetails(customerLoanApplication, id,
				addressProof, panCard, incomeTax, addharCard, photo, signature, bankCheque, salarySlips);

		return new ResponseEntity<CustomerLoanApplication>(details, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/getAllCutomerApplicationData")
	public ResponseEntity<List<CustomerLoanApplication>> getAllCustomerApplicationData()
	{
		List<CustomerLoanApplication> applications= customerLoanApplicationService.getAllCustomerApplicationData();
		return new ResponseEntity<List<CustomerLoanApplication>>(applications,HttpStatus.OK);
	}
}
