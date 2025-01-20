package com.app.service;



import java.util.List;

import com.app.model.EnquiryDetails;

public interface EnquiryDetailsService {

	EnquiryDetails saveDetails(EnquiryDetails enquiryDetails);

	List<EnquiryDetails> getAllEquiryDetails();

	EnquiryDetails getSingleEnquiryDetails(int customerID);

	void deleteEnquiryDetails(int customerID);


}
