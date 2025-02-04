package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Ledger;
import com.app.service.LedgerService;

@RestController
@RequestMapping("/app")
public class LedgerController {
	
	@Autowired
	LedgerService ledgerService ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryDetailscontroller.class);

	
	@PutMapping("/api/saveledgerdata/{customerLoanID}")
	public ResponseEntity<List<Ledger>> ledgerData(@RequestBody Ledger ledger,@PathVariable int customerLoanID){
		LOGGER.info("Received PUT request for Ledger with customerLoanID: {}", customerLoanID);
		List<Ledger> ledgerData=ledgerService.saveLedgerData(ledger,customerLoanID);
		LOGGER.debug("Ledger updated successfully: {}", ledgerData);
		return new ResponseEntity<List<Ledger>>(ledgerData,HttpStatus.ACCEPTED);
	}
}
