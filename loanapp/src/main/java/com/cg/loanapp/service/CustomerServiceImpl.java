package com.cg.loanapp.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.loanapp.dao.CustomerInfoEntity;
import com.cg.loanapp.dao.CustomerInfoRepository;
import com.cg.loanapp.dao.TransacEntity;
import com.cg.loanapp.dao.TransacRepository;
import com.cg.loanapp.dto.CustomerInfo;
import com.cg.loanapp.dto.Transactions;
import com.cg.loanapp.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	LocalDateTime dnt;
	
	@Autowired
	TransacRepository trepo;
	@Autowired
	CustomerInfoRepository cusrepo;

	@Override
	public String showbalance(int accnum) throws CustomerException {
		Optional<CustomerInfoEntity> c = cusrepo.findById(accnum);
		if(c==null)
		throw new CustomerException("Customer does not exsist.");
		List<TransacEntity> tl = trepo.getTransactionList(c.get().getAccnum());	
		int n = tl.size()-1;
		TransacEntity k = tl.get(n);
		double d = k.getBalance();
		return "Your current balance is"+d;
	}
	
	
	@Override
	public String payemi(int accnum) throws CustomerException {
		Optional<CustomerInfoEntity> c = cusrepo.findById(accnum);
		if(c==null)
		throw new CustomerException("Customer does not exsist.");
		if(c.get().getLoanamount()==0)
		throw new CustomerException("You do not have a loan.");
		double p = c.get().getLoanamount();
		double i = c.get().getInterest()/1200;
		double s = c.get().getLoantenure()*12;
		double e = (p*i*(Math.pow((1+i),s)))/((Math.pow((1+i), s))-1);
		int j = (int) Math.round(e);
		List<TransacEntity> t = trepo.getTransactionList(c.get().getAccnum());	
		int n = t.size()-1;
		TransacEntity k = t.get(n);
		double m = k.getBalance();
		TransacEntity te = new TransacEntity();
		te.setCustomerEntity(c.get());
		te.setDebit(e);
		te.setBalance(m-e);
		te.setDnt(dnt.now());
		te.setStatus("EMI paied.");
		trepo.saveAndFlush(te);
		return "EMI paied!";
	}
	
	
	@Override
	public String foreclose(int accnum) throws CustomerException {
		Optional<CustomerInfoEntity> c = cusrepo.findById(accnum);
		if(c==null)
		throw new CustomerException("Customer does not exsist.");
		if(c.get().getLoanamount()==0)
		throw new CustomerException("You do not have a loan.");
		List<TransacEntity> tl = trepo.getTransactionList(c.get().getAccnum());	
		int n = tl.size()-1;
		TransacEntity k = tl.get(n);
		double d = k.getBalance();
		double penalty = d*0.1;
		c.get().setLoanamount(0);
		c.get().setInterest(0);
		c.get().setLoantenure(0);
		c.get().setEmi(0);
		TransacEntity a = new TransacEntity();
		a.setCustomerEntity(c.get());
		a.setStatus("Loan foreclosed.");
		a.setDebit(penalty);
		a.setBalance(0);
		a.setDnt(dnt.now());
		trepo.saveAndFlush(a);
		return "loan foreclosed!";
	}
	
	
	@Override
	public String createaccount(CustomerInfo c) throws CustomerException {
		if(c.getAge()!=0 && c.getEmailId()!=null && c.getName()!=null && c.getOccupation()!=null && c.getIdentityNo()!=0 && c.getGender()!=null) {
		CustomerInfoEntity centity = new CustomerInfoEntity();
		centity.setAge(c.getAge());
		centity.setName(c.getName());
		centity.setEmailId(c.getEmailId());
		centity.setGender(c.getGender());
		centity.setOccupation(c.getOccupation());
		centity.setIdentityNo(c.getIdentityNo());
		cusrepo.saveAndFlush(centity);
		c.setAccnum(centity.getAccnum());
		return "Customer added scuccessfully!"+c.getAccnum();
	}else {
		throw new CustomerException("None of the entries can be null.");
	}
	}
	
	
	@Override
	public List<Transactions> printtransactions(int accnum) throws CustomerException {
		Optional<CustomerInfoEntity> c = cusrepo.findById(accnum);
		if(c==null)
		throw new CustomerException("Customer does not exsist.");
		//System.out.println("Transaction_Id\t\tDate\t\t\tCredit\t\t\tDebit\t\tBalance\t\tStatus");
		List<TransacEntity> tl = trepo.getTransactionList(accnum);
		List<Transactions> transaclist = new ArrayList<Transactions>();
		for (TransacEntity te : tl) {
			Transactions t = new Transactions();
			t.setBalance(te.getBalance());
			t.setCredit(te.getCredit());
			t.setDnt(te.getDnt());
			t.setStatus(te.getStatus());
			t.setTransactionId(te.getTransactionId());
			t.setDebit(te.getDebit());
			CustomerInfo s = new CustomerInfo();
			s.setAge(te.getCustomerEntity().getAge());
			s.setEmailId(te.getCustomerEntity().getEmailId());
			s.setGender(te.getCustomerEntity().getGender());
			s.setName(te.getCustomerEntity().getName());
			s.setOccupation(te.getCustomerEntity().getOccupation());
			s.setIdentityNo(te.getCustomerEntity().getIdentityNo());
			s.setAccnum(te.getCustomerEntity().getAccnum());
			t.setCustomer(s);
			int l = (int) Math.round(t.getCredit());
			int d = (int) Math.round(t.getDebit());
			int b = (int) Math.round(t.getBalance());
			//String e = t.getDnt().format(dtf);
			transaclist.add(t);
			//System.out.println(t.getTransactionId()+"\t\t"+e+"\t\t"+l+"\t\t\t"+d+"\t\t"+b+"\t\t"+t.getStatus());
		}
		return transaclist;
		
	}
	
	
	@Override
	public String applyloan(CustomerInfo c) throws CustomerException {
		Optional<CustomerInfoEntity> m = cusrepo.findById(c.getAccnum());
		if(m==null)
		throw new CustomerException("Customer does not exsist.");
		if(m.get().getLoanamount()!=0) 
		throw new CustomerException("You already have a loan.");
		if(c.getLoanamount()==0 || c.getInterest() ==0 || c.getLoantenure() ==0) 
		throw new CustomerException("None of the enitites can be zero!!");
				m.get().setInterest(c.getInterest());
				m.get().setLoantenure(c.getLoantenure());
				double p = c.getLoanamount();
				double i = c.getInterest()/1200;
				double s =c.getLoantenure()*12;
				double e = (p*i*(Math.pow((1+i),s)))/((Math.pow((1+i), s))-1);
				m.get().setEmi(e);
				m.get().setLoanamount(c.getLoanamount());
				TransacEntity t = new TransacEntity();
				t.setBalance(c.getLoanamount()+(e*s));
				t.setDnt(dnt.now());
				t.setCredit(c.getLoanamount());
				t.setCustomerEntity(m.get());
				t.setStatus("Loan sanctoned!");
				trepo.saveAndFlush(t);
			return "Your loan has been sanctioned!!";
	}

	
	@Override
	public String calculateemi(double loan, double interest, double tenure) throws CustomerException {
		if(loan==0 || interest==0 || tenure==0) {
			throw new CustomerException("None of the entries can be zero.");
		}
		double p = loan;
		double i = interest/1200;
		double s =tenure*12;
		double e = (p*i*(Math.pow((1+i),s)))/((Math.pow((1+i), s))-1);
		int g = (int) Math.round(e);
			return "The emi is "+g;
	}
}



