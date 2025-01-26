package com.app.controller;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.app.model.CustomerLoanApplication;
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
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@PostMapping("/api/enquiry")
	public ResponseEntity<EnquiryDetails> saveDetails(@Valid @RequestBody EnquiryDetails enquiryDetails) {
		LOGGER.info("Received POST request to create Customer: {}", enquiryDetails);
		EnquiryDetails enDetails = enquiryDetailsService.saveDetails(enquiryDetails);
		LOGGER.debug("Customer created successfully: {}", enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}
		
	@GetMapping("/api/getallenquirydetails") 
	public ResponseEntity<List<EnquiryDetails>> getAllEnquiryDetails() {
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		return new ResponseEntity<>(aeDetails, HttpStatus.OK);
	}

	@GetMapping("/api/enquiry/{customerID}")
	public ResponseEntity<EnquiryDetails> getSingleEnquiryDetails(@PathVariable int customerID) {
		LOGGER.info("Received GET request for customer with ID: {}", customerID);
		EnquiryDetails enquiryDetails = enquiryDetailsService.getSingleEnquiryDetails(customerID);
		LOGGER.debug("Returning Customer: {}", enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enquiryDetails, HttpStatus.OK);
	}

	@PutMapping("/api/updatecibil/{customerID}")
	public ResponseEntity<EnquiryDetails> getDataFromCibilScoreData(@PathVariable int customerID) {
		LOGGER.info("Received PUT request for Customer with customerId: {}",customerID);
		String url = "http://localhost:8087/oe/getenquirydata/"+customerID;
		EnquiryDetails enq = rs.getForObject(url, EnquiryDetails.class);
		EnquiryDetails eqEnquiryDetails=enquiryDetailsService.updateEnquiryDetails(enq, customerID);
		LOGGER.debug("Customer updated successfully: {}", eqEnquiryDetails);
		return new ResponseEntity<EnquiryDetails>(eqEnquiryDetails, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/updateenquirydetails")
	public ResponseEntity<EnquiryDetails> updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails) {
		EnquiryDetails enDetails = enquiryDetailsService.updateEnquiry(enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}


	@PutMapping("/api/updateenquirydetails/{customerID}")
	public ResponseEntity<EnquiryDetails> updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails,
			@PathVariable int customerID) {
		EnquiryDetails enDetails = enquiryDetailsService.updateEnquiryDetails(enquiryDetails, customerID);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/enquiry/{customerID}")
	public void deleteEnquiryDetails(@PathVariable int customerID) {
		LOGGER.warn("Received DELETE request for Customer with ID: {}", customerID);
		enquiryDetailsService.deleteEnquiryDetails(customerID);
		LOGGER.info("Customer deleted successfully: {}", customerID);
	}

	

	@PostMapping("/api/getpendingenquiry") 
	public ResponseEntity<List<EnquiryDetails>> getAllPendingEnquiry() {
		List<EnquiryDetails> pendingEquiryList = new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for(EnquiryDetails enq : aeDetails) {
			if(enq.getEnquiryStatus().equals("cibilpending")) {
				pendingEquiryList.add(enq);
			}
		}
		String url = "http://localhost:8087/oe/getpendingstatus";
		rs.postForObject(url, pendingEquiryList, List.class);
		return new ResponseEntity<>(pendingEquiryList, HttpStatus.OK);
	}

	@GetMapping("/api/showrejectedenquiry")
	public ResponseEntity<List<EnquiryDetails>> getRejectedEnquiry(){
		List<EnquiryDetails> rejectedEnquiryList= new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for(EnquiryDetails enq : aeDetails) {
			if(enq.getCibilScoreData()!=null && enq.getCibilScoreData().getCibilScore()<500) {
			rejectedEnquiryList.add(enq);
			}
		}
		return new ResponseEntity<List<EnquiryDetails>>(rejectedEnquiryList,HttpStatus.OK);
	}
	
	@GetMapping("/api/cibilapproved")
	public ResponseEntity<List<EnquiryDetails>> getCibilApproved(){
		List<EnquiryDetails> approvedCibilList= new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for(EnquiryDetails enq : aeDetails) {
			if(enq.getCibilScoreData()!=null && enq.getCibilScoreData().getCibilScore()>=500) {
				approvedCibilList.add(enq);
			}
		}
		return new ResponseEntity<List<EnquiryDetails>>(approvedCibilList,HttpStatus.OK);
	}
	
	@PostMapping("/api/saveCustomerLoanApplicationForm")
	public ResponseEntity<CustomerLoanApplication> customerLoanApplicationForm(@RequestBody CustomerLoanApplication customerLoanApplication)
	{
			 CustomerLoanApplication customerLoanApplication2 = enquiryDetailsService.saveCustomerLoanApplicationForm(customerLoanApplication);
		return new ResponseEntity<CustomerLoanApplication>(customerLoanApplication2,HttpStatus.ACCEPTED);
	}
}
