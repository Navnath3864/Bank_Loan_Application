package com.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CustomerLoanApplication;
import com.app.model.EnquiryDetails;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.EnquiryDetailsService;

@Service
public class EnquiryDetailsServiceImpl implements EnquiryDetailsService {
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;
	
	@Autowired
	CustomerLoanApplicationRepository customerLoanApplicationRepository;

	@Override
     public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails) 
	{
		return enquiryDetailsRepository.save(enquiryDetails);
	}

	
	@Override
    public List<EnquiryDetails> getAllEquiryDetails()
	{
	   return enquiryDetailsRepository.findAll();
	}
	
	public EnquiryDetails getSingleEnquiryDetails(int customerID) 
	{
       return enquiryDetailsRepository.findByCustomerID(customerID);
	}

	public void deleteEnquiryDetails(int customerID) {
		enquiryDetailsRepository.deleteById(customerID);
	}

	@Override
	public EnquiryDetails updateEnquiryDetails(EnquiryDetails enquiryDetails, int customerID) {
		EnquiryDetails enquiryDetails2 = enquiryDetailsRepository.findByCustomerID(customerID);
		if (enquiryDetails2!=null) {
			enquiryDetails2.setFirstName(enquiryDetails.getFirstName());
			enquiryDetails2.setLastName(enquiryDetails.getLastName());
			enquiryDetails2.setEmail(enquiryDetails.getEmail());
//			enquiryDetails2.setEnquiryStatus("cibilgenerated");
			enquiryDetails2.setEnquiryStatus(enquiryDetails.getCibilScoreData().getCibilRemark());
			enquiryDetails2.setAge(enquiryDetails.getAge());
			enquiryDetails2.setMobileNo(enquiryDetails.getMobileNo());
			enquiryDetails2.setPancardNo(enquiryDetails.getPancardNo());
			enquiryDetails2.setCibilScoreData(enquiryDetails.getCibilScoreData());
			enquiryDetailsRepository.save(enquiryDetails2);
			System.out.println(enquiryDetails2);
			return enquiryDetails2;
		}
		return null;
	}

	public CustomerLoanApplication saveCustomerLoanApplicationForm(CustomerLoanApplication customerLoanApplication) {
		int customer_id = customerLoanApplication.getCustomerLoanID();
		EnquiryDetails details = enquiryDetailsRepository.findByCustomerID(customer_id);
		System.out.println(details);
		String name = details.getFirstName()+details.getLastName();
		customerLoanApplication.setCustomerName(name);
		customerLoanApplication.setCustomerAge(details.getAge());
		customerLoanApplication.setCustomerEmail(details.getEmail());
		customerLoanApplication.setCustomerMobileNumber(details.getMobileNo());
		customerLoanApplication.setCibilScoreData(details.getCibilScoreData());	
		CustomerLoanApplication application = customerLoanApplicationRepository.save(customerLoanApplication);
		
		return application;
	}
    @Override
	public EnquiryDetails updateEnquiry(EnquiryDetails enquiryDetails) {
		
			EnquiryDetails e=enquiryDetailsRepository.save(enquiryDetails);
		
		return e;
	}
	


}
