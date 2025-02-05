package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import com.app.model.CustomerLoanApplication;

@RestController
@RequestMapping("/app")
public class CustomerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@PutMapping("/api/updateStatusByCustomer/{customer_ID}")
	public ResponseEntity<CustomerLoanApplication> updateLoanStatusofCustomerApplication(@PathVariable int customer_ID,@RequestBody CustomerLoanApplication customerLoanApplication)
	{
		LOGGER.info("Received PUT request for CustomerController  with customerLoanID: {}", customer_ID);
		String url = "http://localhost:8080/app/api/updateLoanstatus/"+customer_ID;
		restTemplate.put(url, customerLoanApplication, CustomerLoanApplication.class);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}



