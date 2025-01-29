package com.app.service;

import com.app.model.CustomerLoanApplication;
import com.app.model.SanctionLetter;

public interface SanctionService {

	CustomerLoanApplication sanctionLetter(int customerLoanID, SanctionLetter letter);

}
