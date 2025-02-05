package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@PutMapping("/api/genratePdf/{customerLoanID}")
	public ResponseEntity<CustomerLoanApplication> sanctionLetter(@PathVariable int customerLoanID,@RequestBody SanctionLetter letter  )
	{
		LOGGER.info("Received PUT request for CustomerLoanApplication Form to generate pdf of SantionedLetter  with customerLoanID: {}", customerLoanID);
		CustomerLoanApplication application= sanctionService.sanctionLetter(customerLoanID,letter);
		LOGGER.debug("Customerloanapplication Form updated successfully by generating pdf of SantionedLetter: {}", application);
		return new  ResponseEntity<CustomerLoanApplication>(application,HttpStatus.ACCEPTED);
	}
}
