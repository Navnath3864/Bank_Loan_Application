package com.app.service;

import java.util.List;

import com.app.model.Ledger;

public interface LedgerService {

	List<Ledger> saveLedgerData(Ledger ledger,int customerLoanID);
	
}
