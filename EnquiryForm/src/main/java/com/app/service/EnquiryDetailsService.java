package com.app.service;

import com.app.model.EnquiryDetails;

public interface EnquiryDetailsService {

	EnquiryDetails saveDetails(EnquiryDetails enquiryDetails);

	EnquiryDetails getSingleEnquiryDetails(int customerID);

}
