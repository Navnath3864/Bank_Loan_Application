package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CustomerLoanApplication;
import com.app.service.CustomerLoanApplicationService;

@RestController
@RequestMapping("/app")
public class CustomerLoanApplicationController {

	@Autowired
	CustomerLoanApplicationService customerLoanApplicationService; 
	@PostMapping("/api/customerloanapplication")
	public ResponseEntity<CustomerLoanApplication> saveDetails(@RequestBody CustomerLoanApplication customerLoanApplication)
	{
		CustomerLoanApplication details=customerLoanApplicationService.saveDetails(customerLoanApplication);
		return new ResponseEntity<CustomerLoanApplication>(details, HttpStatus.CREATED);
	}
}
