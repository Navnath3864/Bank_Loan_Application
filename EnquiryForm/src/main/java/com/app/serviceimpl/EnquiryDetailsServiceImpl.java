package com.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.InValidAgeException;
import com.app.exception.InValidEmailException;
import com.app.exception.InValidFirstNameException;

import com.app.exception.InValidLastNameException;
import com.app.exception.InValidMobileNoException;
import com.app.exception.InValidPancardNoException;
import com.app.model.EnquiryDetails;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.EnquiryDetailsService;
import com.app.validation.Validator;

@Service
public class EnquiryDetailsServiceImpl implements EnquiryDetailsService {
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;
	
	@Autowired
	Validator validation;

	@Override
	public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails) {
		
		validation.validateEnquiryDetails(enquiryDetails);
		return enquiryDetailsRepository.save(enquiryDetails);
	}

	
	@Override
    public List<EnquiryDetails> getAllEquiryDetails() {
		return enquiryDetailsRepository.findAll();
	}
	
	public EnquiryDetails getSingleEnquiryDetails(int customerID) {

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
			enquiryDetails2.setAge(enquiryDetails.getAge());
			enquiryDetails2.setMobileNo(enquiryDetails.getMobileNo());
			enquiryDetails2.setPancardNo(enquiryDetails.getPancardNo());
			enquiryDetails2.setCibilScoreData(enquiryDetails.getCibilScoreData());
			enquiryDetailsRepository.save(enquiryDetails2);
			return enquiryDetails2;
		}
		return null;
	}
	
	
	

}
