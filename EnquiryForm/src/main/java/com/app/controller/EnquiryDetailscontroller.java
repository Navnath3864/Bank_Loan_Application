package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EnquiryDetails;
import com.app.service.EnquiryDetailsService;

@RestController
public class EnquiryDetailscontroller {

	@Autowired
	EnquiryDetailsService enquiryDetailsService;

	@PostMapping("/api/enquiry")
	public ResponseEntity<EnquiryDetails> saveDetails(@RequestBody EnquiryDetails enquiryDetails) {
		EnquiryDetails enDetails = enquiryDetailsService.saveDetails(enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}
}
