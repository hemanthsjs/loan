package com.cg.loanapp.service;

import java.util.List;

import com.cg.loanapp.dto.CustomerInfo;
import com.cg.loanapp.dto.Transactions;
import com.cg.loanapp.exception.CustomerException;

public interface CustomerService {
	
	String showbalance(int accnum) throws CustomerException;
	String payemi(int accnum)throws CustomerException;
	String foreclose(int accnum)throws CustomerException;
	String createaccount(CustomerInfo c)throws CustomerException;
	List<Transactions> printtransactions(int accnum)throws CustomerException;
	String applyloan(CustomerInfo c)throws CustomerException;
	String calculateemi(double loan, double interest, double tenure)throws CustomerException;

}
