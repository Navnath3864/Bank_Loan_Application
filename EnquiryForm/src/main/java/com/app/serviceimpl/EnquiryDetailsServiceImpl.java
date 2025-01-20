package com.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.EnquiryDetails;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.EnquiryDetailsService;

@Service
public class EnquiryDetailsServiceImpl implements EnquiryDetailsService {
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;

	@Override
	public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails) {
		return enquiryDetailsRepository.save(enquiryDetails);
	}

	@Override

	public List<EnquiryDetails> getAllEquiryDetails() {
		return enquiryDetailsRepository.findAll();
		}
	
	public EnquiryDetails getSingleEnquiryDetails(int customerID) {

		return enquiryDetailsRepository.findByCustomerID(customerID);
	}

	@Override

	public EnquiryDetails updateEnquiryDetails( EnquiryDetails enquiryDetails) {
		Optional<EnquiryDetails> endetails=enquiryDetailsRepository.findById(enquiryDetails.getCustomerID());
		if(endetails.isPresent()) {
			endetails.get().setFirstName(enquiryDetails.getFirstName());
			endetails.get().setLastName(enquiryDetails.getLastName());
			endetails.get().setAge(enquiryDetails.getAge());
			endetails.get().setMobileNo(enquiryDetails.getMobileNo());
			endetails.get().setEmail(enquiryDetails.getEmail());
			endetails.get().setPancardNo(enquiryDetails.getPancardNo());
			enquiryDetailsRepository.save(endetails.get());
			return endetails.get();
		}
		return null;
	}
	public void deleteEnquiryDetails(int customerID) {
		enquiryDetailsRepository.deleteById(customerID);
		
	}

}
