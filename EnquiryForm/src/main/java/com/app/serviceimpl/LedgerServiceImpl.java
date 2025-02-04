package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CustomerLoanApplication;
import com.app.model.Ledger;
import com.app.repository.LedgerRepository;
import com.app.service.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {

	@Autowired
	LedgerRepository ledgerRepository;

	@Autowired
	CustomerLoanApplicationServiceImpl customerLoanApplicationServiceImpl;

	@Override
	public Ledger saveLedgerData(Ledger ledger, int customerLoanID) {
		CustomerLoanApplication custApplication = customerLoanApplicationServiceImpl
				.getCustomerLoanApplication(customerLoanID);
		ledger.setLedgerId(custApplication.getCustomerLoanID());
		ledger.setTotalLoanAmount(custApplication.getLoandisbursement().getTotalAmount());
		ledger.setTenure(custApplication.getRequiredTenure());
		ledger.setMonthlyEMI(ledger.getTotalLoanAmount() / ledger.getTenure());
		ledger.setRemainingAmount(ledger.getTotalLoanAmount() - ledger.getAmountPaidtillDate());

		return ledgerRepository.save(ledger);

	}

}
