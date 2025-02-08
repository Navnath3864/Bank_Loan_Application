package com.app.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CustomerLoanApplication;
import com.app.model.Ledger;
import com.app.repository.LedgerRepository;
import com.app.service.EmailService;
import com.app.service.LedgerService;

@Service
public class LedgerServiceImpl implements LedgerService {

	@Autowired
	LedgerRepository ledgerRepository;
	
	@Autowired
	EmailService emailService;

	@Autowired
	CustomerLoanApplicationServiceImpl customerLoanApplicationServiceImpl;

	@Override
	public List<Ledger> saveLedgerData(Ledger ledger1, int customerLoanID) {
		CustomerLoanApplication custApplication = customerLoanApplicationServiceImpl
				.getCustomerLoanApplication(customerLoanID);
		int count = 0;
		List<Ledger> ledgers = custApplication.getLedger();

		while (count < custApplication.getRequiredTenure()) {
			Ledger ledger = new Ledger();
			ledger.setNextEmiDatestart(ledger1.getNextEmiDatestart());
			ledger.setNextEmiDateEnd(ledger1.getNextEmiDateEnd());
			ledger.setLoanEndDate(ledger.getLoanEndDate());
			ledger.setTotalLoanAmount(custApplication.getLoandisbursement().getTotalAmount());
			ledger.setTenure(custApplication.getRequiredTenure());
			ledger.setPayableAmountwithInterest(ledger.getPayableAmountwithInterest());
			ledger.setMonthlyEMI(ledger.getTotalLoanAmount() / ledger.getTenure());
			ledger.setLoanStatus("pending");
			ledgers.add(ledger);
			count++;
		}

		ledgerRepository.saveAll(ledgers);
		custApplication.getLedger().addAll(ledgers);
		List<Ledger> al =custApplication.getLedger();
		return al  ;
	}

	@Override
	public Ledger updateledger(int id, String option,int customer_id) {

		Optional<Ledger> ledger = ledgerRepository.findById(id);
		Optional<Ledger> led = ledgerRepository.findById(id - 1);
		if (led.isEmpty()) {
			Ledger ledger2 = ledger.get();
			if ("pay".equals(option)) {
				double emi = ledger2.getMonthlyEMI();
				ledger2.setRemainingAmount(ledger2.getTotalLoanAmount() - emi);
				ledger2.setCurrentMonthEmiStatus("paid");
				ledger2.setAmountPaidtillDate(emi);
				ledger2.setPreviousEmitStatus("unpaid");
				ledger2.setMonthlyEMI(0.0);
				if (ledger2.getRemainingAmount() ==0) {
					ledger2.setLoanStatus("nill");
				}
				emailService.sentLegderStatusToCustomerMail(customer_id, ledger2);
				ledgerRepository.save(ledger2);
				return ledger2;
			}

			if ("skip".equals(option)) {
				Optional<Ledger> ledger1 = ledgerRepository.findById(id + 1);
				Ledger ledger3 = ledger1.get();
				double emi = ledger2.getMonthlyEMI();
				ledger3.setMonthlyEMI(emi + ledger3.getMonthlyEMI());
				ledger2.setCurrentMonthEmiStatus("unpaid");
				ledgerRepository.save(ledger3);
				emailService.sentLegderStatusToCustomerMail(customer_id, ledger3);
				return ledger2;

			}
		} else {
			if (led != null) {
				Ledger leg = led.get();
				Ledger ledger2 = ledger.get();
				if ("pay".equals(option)) {
					double emi = ledger2.getMonthlyEMI();
					ledger2.setRemainingAmount(leg.getRemainingAmount() - emi);
					ledger2.setCurrentMonthEmiStatus("paid");
					ledger2.setAmountPaidtillDate(leg.getAmountPaidtillDate() + emi);
					ledger2.setPreviousEmitStatus(leg.getCurrentMonthEmiStatus());
					ledger2.setMonthlyEMI(0.0);
					if (ledger2.getRemainingAmount() ==0) {
						ledger2.setLoanStatus("nill");
					}
					emailService.sentLegderStatusToCustomerMail(customer_id, ledger2);
					ledgerRepository.save(ledger2);
					return ledger2;
				}

				if ("skip".equals(option)) {
					Optional<Ledger> ledger1 = ledgerRepository.findById(id + 1);
					Ledger ledger3 = ledger1.get();
					double emi = ledger2.getMonthlyEMI();
					ledger3.setMonthlyEMI(emi + ledger3.getMonthlyEMI());
					ledger2.setCurrentMonthEmiStatus("unpaid");
					emailService.sentLegderStatusToCustomerMail(customer_id, ledger2);
					ledgerRepository.save(ledger3);
					return ledger2;

				}
			}

		}
		return null;

	}
	 
}
