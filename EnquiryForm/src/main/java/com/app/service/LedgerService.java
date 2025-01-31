package com.app.service;

import com.app.model.Ledger;

public interface LedgerService {

	Ledger saveLedgerData(Ledger ledger,int customerLoanID);
	
}
