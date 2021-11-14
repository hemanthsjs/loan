package com.cg.loanapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.loanapp.service.*;
import com.cg.loanapp.dto.CustomerInfo;
import com.cg.loanapp.dto.Transactions;
import com.cg.loanapp.exception.CustomerException;

  

@RestController
public class LoanAppController {

	 @Autowired
	 CustomerService cs;
	
	
	@PostMapping("/createaccount")
	public ResponseEntity<String> createaccount(@RequestBody CustomerInfo c) throws CustomerException {
		try {
			String msg =  cs.createaccount(c);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}
	}
	
	@PutMapping("/applyloan")
	public ResponseEntity<String> applyloan(@RequestBody CustomerInfo c) throws CustomerException{
		try {
			String msg =  cs.applyloan(c);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		} 
	}
	@GetMapping("/showbalance/{accnum}")
	public ResponseEntity<String> showbalance(@PathVariable int accnum) throws CustomerException {
		try {
			String msg = cs.showbalance(accnum);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		} 
	}
	@PutMapping("/payemi/{accnum}")
	public ResponseEntity<String> payemi(@PathVariable int accnum) throws CustomerException {
		try {
			String msg = cs.payemi(accnum);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		} 
	}
	@PutMapping("/foreclose/{accnum}")
	public ResponseEntity<String> foreclose(@PathVariable int accnum) throws CustomerException {
		try {
			String msg =  cs.foreclose(accnum);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		} 
	}
	@GetMapping("/calculateemi/{loan}/{interest}/{tenure}")
	public ResponseEntity<String> calculateemi(@PathVariable double loan, @PathVariable double interest, @PathVariable double tenure) throws CustomerException {
		try {
			String msg =  cs.calculateemi(loan, interest, tenure);
			return ResponseEntity.ok(msg);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}
	}
	@GetMapping("/printtransactions/{accnum}")
	public ResponseEntity<List<Transactions>> printtransactions(@PathVariable int accnum) throws CustomerException {
	try {
		List<Transactions> tl =  cs.printtransactions(accnum);
		return ResponseEntity.ok(tl);
	} catch (Exception e) {
		throw new CustomerException(e.getMessage());
	}
}
}
