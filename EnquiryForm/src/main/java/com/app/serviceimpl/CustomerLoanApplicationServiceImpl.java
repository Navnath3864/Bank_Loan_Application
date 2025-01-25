package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.CustomerLoanApplication;
import com.app.model.EnquiryDetails;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.CustomerLoanApplicationService;

@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationService{

	@Autowired
	CustomerLoanApplicationRepository customerLoanApplicationRepository;
	
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;
	
//	@Override
//	public CustomerLoanApplication saveDetails(CustomerLoanApplication customerLoanApplication) {
//		
//		String emai = customerLoanApplication.getCustomerEmail();
//		
//		CustomerLoanApplication details=customerLoanApplicationRepository.save(customerLoanApplication);
//		return details;
//	}

	@Override
	public CustomerLoanApplication saveDetails(CustomerLoanApplication customerLoanApplication, int id) {

		EnquiryDetails details   = enquiryDetailsRepository.findByCustomerID(id);
		if (details!=null) {
//			customerLoanApplication.setCustomerID(id);
			customerLoanApplication.setCustomerName(details.getFirstName()+details.getLastName());
			customerLoanApplication.setCustomerAge(details.getAge());
			customerLoanApplication.setCustomerEmail(details.getEmail());
			customerLoanApplication.setCustomerMobileNumber(details.getMobileNo());
			customerLoanApplication.setCibilScoreData(details.getCibilScoreData());
			return customerLoanApplicationRepository.save(customerLoanApplication);
		}
		
		
		return null;
	}

}
