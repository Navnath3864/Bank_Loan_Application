package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.CustomerLoanApplication;
import com.app.service.CustomerLoanApplicationService;

@RestController
@RequestMapping("/app")
public class CustomerLoanApplicationController {

	@Autowired
	CustomerLoanApplicationService customerLoanApplicationService;
	
	@Autowired
	RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	@PostMapping("/api/customerloanapplication/{id}")
	public ResponseEntity<CustomerLoanApplication> saveDetails(@RequestPart("data") String customerLoanApplication,
			@RequestPart("addressProof") MultipartFile addressProof, @RequestPart("panCard") MultipartFile panCard,
			@RequestPart("incomeTax") MultipartFile incomeTax, @RequestPart("addharCard") MultipartFile addharCard,
			@RequestPart("photo") MultipartFile photo, @RequestPart("signature") MultipartFile signature,
			@RequestPart("bankCheque") MultipartFile bankCheque, @RequestPart("salarySlips") MultipartFile salarySlips,
			@PathVariable int id) {
		LOGGER.info("Received POST request to create Customerloanapplication Form");
		CustomerLoanApplication details = customerLoanApplicationService.saveDetails(customerLoanApplication, id,
				addressProof, panCard, incomeTax, addharCard, photo, signature, bankCheque, salarySlips);

		LOGGER.debug("Customerloanapplication Form created successfully: {}", details);

		return new ResponseEntity<CustomerLoanApplication>(details, HttpStatus.CREATED);
	}

	@GetMapping("/api/getAllCutomerApplicationData")
	public ResponseEntity<List<CustomerLoanApplication>> getAllCustomerApplicationData() {
		LOGGER.info("Received GET request to fetch all Customerloanapplication Form");
		List<CustomerLoanApplication> applications = customerLoanApplicationService.getAllCustomerApplicationData();
		LOGGER.debug("Fetched {} Customerloanapplication Form successfully", applications.size());
		return new ResponseEntity<List<CustomerLoanApplication>>(applications, HttpStatus.OK);
	}

	@PutMapping("/api/updateLoanstatus/{id}")
	public ResponseEntity<CustomerLoanApplication> updateLoanStatus(
			@RequestBody CustomerLoanApplication customerLoanApplication, @PathVariable int id) {
		LOGGER.info("Received PUT request for Customerloanapplication Form with customerLoanID: {}", id);
		CustomerLoanApplication application = customerLoanApplicationService.updateLoanStatus(id,
				customerLoanApplication.getLoanStatus());
		LOGGER.debug("Customerloanapplication Form updated successfully: {}", application);
		return new ResponseEntity<CustomerLoanApplication>(application, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/getAllLoansubmited")
	public ResponseEntity<List<CustomerLoanApplication>> getAllLoansubmited() {
		LOGGER.info("Received GET request to fetch all Customerloanapplication Form whose loanStatus is Submitted");
		List<CustomerLoanApplication> list = customerLoanApplicationService.getAllLoansubmited();
		LOGGER.debug("Fetched {} Customerloanapplication Form successfully whose loanStatus is Submitted", list.size());
		return new ResponseEntity<List<CustomerLoanApplication>>(list, HttpStatus.OK);
	}

	@GetMapping("/api/getAllVerifiedData")
	public ResponseEntity<List<CustomerLoanApplication>> getAllVerifiedData() {
		LOGGER.info("Received GET request to fetch all Customerloanapplication Form whose loanStatus is Verified");
		List<CustomerLoanApplication> list = customerLoanApplicationService.getAllVerifiedData();
		LOGGER.debug("Fetched {} Customerloanapplication Form successfully whose loanStatus is Verified", list.size());
		return new ResponseEntity<List<CustomerLoanApplication>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/api/updateLoanStatusofCustomerApplication/{id}")
	public ResponseEntity<CustomerLoanApplication> updateLoanStatusofCustomerApplication(
			@RequestBody CustomerLoanApplication customerLoanApplication, @PathVariable int id) {
		LOGGER.info("Received PUT request for CustomerController  with customerLoanID: {}", id);
		CustomerLoanApplication application = customerLoanApplicationService.updateLoanStatusofCustomerApplication(id,
				customerLoanApplication.getLoanStatus());
		LOGGER.debug("Customerloanapplication Form updated successfully: {}", application);
		return new ResponseEntity<CustomerLoanApplication>(application, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/getAllSanctionedData")
	public ResponseEntity<List<CustomerLoanApplication>> getALlSanctioedData()
	{
		 List<CustomerLoanApplication> list = customerLoanApplicationService.getAllSanctioedData();
		return new  ResponseEntity<List<CustomerLoanApplication>>(list,HttpStatus.OK);
	}
}