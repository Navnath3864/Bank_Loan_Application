package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.CustomerLoanApplication;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.service.CustomerLoanApplicationService;

@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationService{

	@Autowired
	CustomerLoanApplicationRepository customerLoanApplicationRepository;
	
	@Override
	public CustomerLoanApplication saveDetails(CustomerLoanApplication customerLoanApplication) {
		
		return customerLoanApplicationRepository.save(customerLoanApplication);
		
	}

}
