package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CustomerLoanApplication;
import com.app.model.SanctionLetter;
import com.app.service.SanctionService;

@RestController
@RequestMapping("/app")
public class SanctionController {
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	SanctionService sanctionService;

	
	@PutMapping("/api/genratePdf/{customerLoanID}")
	public ResponseEntity<CustomerLoanApplication> sanctionLetter(@PathVariable int customerLoanID,@RequestBody SanctionLetter letter  )
	{
		CustomerLoanApplication application= sanctionService.sanctionLetter(customerLoanID,letter);
		return new  ResponseEntity<CustomerLoanApplication>(application,HttpStatus.ACCEPTED);
	}
}
