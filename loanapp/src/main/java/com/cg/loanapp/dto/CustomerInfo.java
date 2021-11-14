package com.cg.loanapp.dto;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Component
public class CustomerInfo{
	private int accnum;	
	private String name;	
	private String emailId;	
	private String occupation;	
	private int identityNo;	
	private String gender;	
	private int age;
	private double Loanamount;	
	private double loantenure;	
	private double interest;
	private double emi=0;
	//@OneToMany(mappedBy = "customer")
	//private List<Transactions> tl;
	
	
	
	

//	public CustomerInfo(int accnum, String name, String emailId, String occupation, int identityNo, String gender,
//			int age, double loanamount, double loantenure, double interest, double emi, List<Transactions> tl) {
//		super();
//		this.accnum = accnum;
//		this.name = name;
//		this.emailId = emailId;
//		this.occupation = occupation;
//		this.identityNo = identityNo;
//		this.gender = gender;
//		this.age = age;
//		Loanamount = loanamount;
//		this.loantenure = loantenure;
//		this.interest = interest;
//		this.emi = emi;
//		this.tl = tl;
//	}
//	
//	public CustomerInfo() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	

	public int getAccnum() {
		return accnum;
	}

	public void setAccnum(int accnum) {
		this.accnum = accnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(int identityNo) {
		this.identityNo = identityNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getLoanamount() {
		return Loanamount;
	}

	public void setLoanamount(double loanamount) {
		Loanamount = loanamount;
	}

	public double getLoantenure() {
		return loantenure;
	}

	public void setLoantenure(double loantenure) {
		this.loantenure = loantenure;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

//	public List<Transactions> getTl() {
//		return tl;
//	}
//
//	public void setTl(List<Transactions> tl) {
//		this.tl = tl;
//	}

	
	
	

}



