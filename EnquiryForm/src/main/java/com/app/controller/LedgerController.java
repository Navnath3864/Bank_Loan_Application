package com.app.controller;

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
	
	
	@PutMapping("/api/saveledgerdata/{customerLoanID}")
	public ResponseEntity<Ledger> ledgerData(@RequestBody Ledger ledger,@PathVariable int customerLoanID){
		Ledger ledgerData=ledgerService.saveLedgerData(ledger,customerLoanID);
		return new ResponseEntity<Ledger>(ledgerData,HttpStatus.ACCEPTED);
	}
}
