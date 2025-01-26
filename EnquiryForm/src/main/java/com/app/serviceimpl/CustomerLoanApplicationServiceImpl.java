package com.app.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.AllPersonalDocs;
import com.app.model.CustomerLoanApplication;
import com.app.model.EnquiryDetails;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.CustomerLoanApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationService{

	@Autowired
	CustomerLoanApplicationRepository customerLoanApplicationRepository;
	
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;
	 
@Override
public CustomerLoanApplication saveDetails(String customerLoanApplication1, int id, MultipartFile addressProof,
		MultipartFile panCard, MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo,
		MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips) {
	CustomerLoanApplication customerLoanApplication = null; 
	EnquiryDetails details = enquiryDetailsRepository.findByCustomerID(id);
	ObjectMapper objectMapper = new ObjectMapper();

	try {
	    customerLoanApplication = objectMapper.readValue(
	            customerLoanApplication1, CustomerLoanApplication.class);
	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}
	if (customerLoanApplication != null) {
	    customerLoanApplication.setCustomerName(details.getFirstName() + details.getLastName());
	    customerLoanApplication.setCustomerAge(details.getAge());
	    customerLoanApplication.setCustomerEmail(details.getEmail());
	    customerLoanApplication.setCustomerMobileNumber(details.getMobileNo());
	    customerLoanApplication.setCibilScoreData(details.getCibilScoreData());
	}   
	    if (customerLoanApplication.getAllPersonalDocument() == null) {
            customerLoanApplication.setAllPersonalDocument(new AllPersonalDocs());
        }
	    
	    try {
	    	customerLoanApplication.getAllPersonalDocument().setAddressProof(addressProof.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setAddharCard(addharCard.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setPanCard(panCard.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setIncomeTax(incomeTax.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setPhoto(photo.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setSignature(signature.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setBankCheque(bankCheque.getBytes());
	        customerLoanApplication.getAllPersonalDocument().setSalarySlips(salarySlips.getBytes());
	        System.out.println(customerLoanApplication);
	        customerLoanApplicationRepository.save(customerLoanApplication);
	      
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	

	return customerLoanApplication;

}
	
	

}
