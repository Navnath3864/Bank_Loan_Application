package com.app.service;

import com.app.model.CustomerLoanApplication;

public interface EmailService {

	CustomerLoanApplication sendSanctionLetterMailToCustomer(int customer_ID);

}
