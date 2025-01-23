package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.model.EnquiryDetails;
import com.app.service.EnquiryDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class EnquiryDetailscontroller {

	@Autowired
	RestTemplate rs;

	@Autowired
	EnquiryDetailsService enquiryDetailsService;

	@PostMapping("/api/enquiry")
	public ResponseEntity<EnquiryDetails> saveDetails(@Valid @RequestBody EnquiryDetails enquiryDetails) {
		EnquiryDetails enDetails = enquiryDetailsService.saveDetails(enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}
		
	@GetMapping("/api/getallenquirydetails") 
	public ResponseEntity<List<EnquiryDetails>> getAllEnquiryDetails() {
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		return new ResponseEntity<>(aeDetails, HttpStatus.OK);
	}

	@GetMapping("/api/enquiry/{customerID}")
	public ResponseEntity<EnquiryDetails> getSingleEnquiryDetails(@PathVariable int customerID) {
		EnquiryDetails enquiryDetails = enquiryDetailsService.getSingleEnquiryDetails(customerID);
		return new ResponseEntity<EnquiryDetails>(enquiryDetails, HttpStatus.OK);
	}

	@PutMapping("/api/updateenquirydetails/{customerID}")
	public ResponseEntity<EnquiryDetails> updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails,
			@PathVariable int customerID) {
		EnquiryDetails enDetails = enquiryDetailsService.updateEnquiryDetails(enquiryDetails, customerID);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/api/enquiry/{customerId}")
	public void deleteEnquiryDetails(@PathVariable int customerID) {
		enquiryDetailsService.deleteEnquiryDetails(customerID);
	}

	@PutMapping("/api/updatecibil/{customerID}")
	public ResponseEntity<EnquiryDetails> getDataFromCibilScoreData(@PathVariable int customerID) {
		String url = "http://localhost:8087/oe/getenquirydata/" + customerID;
		EnquiryDetails enq = rs.getForObject(url, EnquiryDetails.class);
		System.out.println(enq);
		enquiryDetailsService.updateEnquiryDetails(enq, customerID);
		return new ResponseEntity<EnquiryDetails>(enq, HttpStatus.ACCEPTED);
	}

}
