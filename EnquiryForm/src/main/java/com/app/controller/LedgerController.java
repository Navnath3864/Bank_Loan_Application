package com.app.controller;

import java.util.List;

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
	public ResponseEntity<List<Ledger>> ledgerData(@RequestBody Ledger ledger,@PathVariable int customerLoanID){
		List<Ledger> ledgerData=ledgerService.saveLedgerData(ledger,customerLoanID);
		return new ResponseEntity<List<Ledger>>(ledgerData,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/updateledgerdata/{id}/{option}")
	public ResponseEntity<Ledger> saveledger(@PathVariable("id") int id,@PathVariable("option") String option)
	{
		Ledger ledger =ledgerService.updateledger(id,option);
		return new ResponseEntity<Ledger>(ledger,HttpStatus.ACCEPTED);
	}
}
