package com.app.serviceimpl;

import java.util.List;
import java.util.Optional;

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
	public List<Ledger> saveLedgerData(Ledger ledger1, int customerLoanID) {
	    CustomerLoanApplication custApplication = customerLoanApplicationServiceImpl
	            .getCustomerLoanApplication(customerLoanID);
	    int count = 0;
	    List<Ledger> ledgers = custApplication.getLedger();

	    while ( count < custApplication.getRequiredTenure() ) {
	        Ledger ledger = new Ledger();
	        ledger.setTotalLoanAmount(custApplication.getLoandisbursement().getTotalAmount());
	        ledger.setTenure(custApplication.getRequiredTenure());
	        ledger.setMonthlyEMI(ledger.getTotalLoanAmount() / ledger.getTenure());
	        ledgers.add(ledger);
	        count++;
	    }

	    ledgerRepository.saveAll(ledgers); 
	    custApplication.getLedger().addAll(ledgers); 
	    return custApplication.getLedger();
	}


	@Override
	public Ledger updateledger(int id, String option) {
		
		if("pay".equals(option))
		{
			Optional<Ledger> ledger = ledgerRepository.findById(id);
			Ledger ledger2 =ledger.get();
			double p = 0;
			double  h = ledger2.getMonthlyEMI();
			
			ledger2.setRemainingAmount(ledger2.getRemainingAmount()-h);
			System.out.println(ledger2.getMonthlyEMI());
			System.out.println(ledger2.getTotalLoanAmount());
			ledger2.setMonthlyEMI(p);
			ledgerRepository.save(ledger2);
			return ledger2;
			
		}
		if("skip".equals(option))
		{
			Optional<Ledger> ledger = ledgerRepository.findById(id);
			Ledger ledger2 =ledger.get();
			
			Optional<Ledger> ledger1 = ledgerRepository.findById(id+1);
			Ledger ledger3 =ledger1.get();
			double  h = ledger2.getMonthlyEMI();
			ledger3.setMonthlyEMI(h+ledger3.getMonthlyEMI());
			ledgerRepository.save(ledger3);
			return ledger2;
			
		}
		return null;
		
		
	}

}
