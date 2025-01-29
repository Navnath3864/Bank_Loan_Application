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

	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@PostMapping("/api/enquiry")
	public ResponseEntity<EnquiryDetails> saveDetails(@Valid @RequestBody EnquiryDetails enquiryDetails) {
		LOGGER.info("Received POST request to create Customer: {}", enquiryDetails);
		EnquiryDetails enDetails = enquiryDetailsService.saveDetails(enquiryDetails);
		LOGGER.debug("Customer created successfully: {}", enquiryDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/getallenquirydetails")
	public ResponseEntity<List<EnquiryDetails>> getAllEnquiryDetails() {
		LOGGER.info("Received GET request to fetch all enquiry details");
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		LOGGER.debug("Fetched {} enquiry details successfully", aeDetails.size());
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
		LOGGER.info("Received PUT request for Customer with customerId: {}", customerID);
		String url = "http://localhost:8087/oe/getenquirydata/" + customerID;
		EnquiryDetails enq = rs.getForObject(url, EnquiryDetails.class);
		EnquiryDetails eqEnquiryDetails = enquiryDetailsService.updateEnquiryDetails(enq, customerID);
		LOGGER.debug("Customer CibilScore updated successfully: {}", eqEnquiryDetails);
		return new ResponseEntity<EnquiryDetails>(eqEnquiryDetails, HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/updateenquirydetails")
	public ResponseEntity<EnquiryDetails> updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails) {
		LOGGER.info("Received PUT request for update enquiry details:{}", enquiryDetails);
		EnquiryDetails enDetails = enquiryDetailsService.updateEnquiry(enquiryDetails);
		LOGGER.debug("Customer updated successfully: {}", enDetails);
		return new ResponseEntity<EnquiryDetails>(enDetails, HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/updateenquirydetails/{customerID}")
	public ResponseEntity<EnquiryDetails> updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails,
			@PathVariable int customerID) {
		LOGGER.info("Received PUT request for Customer with customerId: {}", customerID);
		EnquiryDetails enDetails = enquiryDetailsService.updateEnquiryDetails(enquiryDetails, customerID);
		LOGGER.debug("Customer updated successfully: {}", enDetails);
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
		LOGGER.info("Received POST request to fetch enquirydetails whose CibilScroe is pending");
		List<EnquiryDetails> pendingEquiryList = new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for (EnquiryDetails enq : aeDetails) {
			if (enq.getEnquiryStatus().equals("cibilpending")) {
				pendingEquiryList.add(enq);
			}
		}
		String url = "http://localhost:8087/oe/getpendingstatus";
		rs.postForObject(url, pendingEquiryList, List.class);
		LOGGER.debug("Fetched {} enquirydetails Successfully whose cibilscore is pending", pendingEquiryList.size());
		return new ResponseEntity<>(pendingEquiryList, HttpStatus.OK);
	}

	@GetMapping("/api/showrejectedenquiry")
	public ResponseEntity<List<EnquiryDetails>> getRejectedEnquiry() {
		LOGGER.info("Received GET request to fetch enquirydetails whose CibilScore is rejected");
		List<EnquiryDetails> rejectedEnquiryList = new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for (EnquiryDetails enq : aeDetails) {
			if (enq.getCibilScoreData() != null && enq.getCibilScoreData().getCibilScore() < 500) {
				rejectedEnquiryList.add(enq);
			}
		}
		LOGGER.debug("Fetched {} enquirydetails Successfully whose cibilscore is rejected", rejectedEnquiryList.size());
		return new ResponseEntity<List<EnquiryDetails>>(rejectedEnquiryList, HttpStatus.OK);
	}

	@GetMapping("/api/cibilapproved")
	public ResponseEntity<List<EnquiryDetails>> getCibilApproved() {
		LOGGER.info("Received GET request to fetch enquirydetails whose CibilScore is approved");
		List<EnquiryDetails> approvedCibilList = new ArrayList<EnquiryDetails>();
		List<EnquiryDetails> aeDetails = (List<EnquiryDetails>) enquiryDetailsService.getAllEquiryDetails();
		for (EnquiryDetails enq : aeDetails) {
			if (enq.getCibilScoreData() != null && enq.getCibilScoreData().getCibilScore() >= 500) {
				approvedCibilList.add(enq);
			}
		}
		LOGGER.debug("Fetched {} enquirydetails Successfully whose cibilscore is approved", approvedCibilList.size());
		return new ResponseEntity<List<EnquiryDetails>>(approvedCibilList, HttpStatus.OK);

	}


}