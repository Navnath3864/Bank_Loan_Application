package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CustomerLoanApplication;
import com.app.service.CustomerLoanApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class CustomerLoanApplicationController {

	@Autowired
	CustomerLoanApplicationService customerLoanApplicationService; 
	@PostMapping("/api/customerloanapplication/{id}")
	public ResponseEntity<CustomerLoanApplication> saveDetails(@Valid @RequestBody CustomerLoanApplication customerLoanApplication,@PathVariable int id)
	{
		CustomerLoanApplication details=customerLoanApplicationService.saveDetails(customerLoanApplication,id);
		return new ResponseEntity<CustomerLoanApplication>(details, HttpStatus.CREATED);
	}
}
