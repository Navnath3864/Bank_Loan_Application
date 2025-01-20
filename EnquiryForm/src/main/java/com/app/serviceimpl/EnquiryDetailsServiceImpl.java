package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.EnquiryDetails;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.EnquiryDetailsService;
@Service
public class EnquiryDetailsServiceImpl implements EnquiryDetailsService{
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;

	@Override
	public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails) {
		return enquiryDetailsRepository.save(enquiryDetails);
	}

}
