package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CustomerLoanApplication;
import com.app.service.EmailService;

@RestController
@RequestMapping("/app")
public class EmailController {
	
	@Autowired
	EmailService  emailService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@GetMapping("/api/sendSantionLetterMail/{customerLoan_ID}")
	public ResponseEntity<CustomerLoanApplication> sendSanctionLetterMail(@PathVariable int customerLoan_ID)
	{
		LOGGER.info("Received GET request for CustomerLoanApplication Form to send SantionLetter mail with customerLoan_ID:{}"+customerLoan_ID);
		CustomerLoanApplication customerLoanApplication = emailService.sendSanctionLetterMailToCustomer(customerLoan_ID);
		LOGGER.debug("Fetch Customerloanapplication Form with Santion Letter successfully: {}", customerLoanApplication);
		return new ResponseEntity<CustomerLoanApplication>(customerLoanApplication,HttpStatus.OK);
		
	}
	

}
