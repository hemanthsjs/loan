package com.cg.loanapp.dao;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cg.loanapp.dto.CustomerInfo;


@Entity
@Table(name = "transactions")
public class TransacEntity {
		
		@Id
		@SequenceGenerator(name="transactionseq", sequenceName = "transacseq", allocationSize = 1, initialValue = 100)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionseq")
		@Column(name = "transaction_id")
		private int transactionId;
		private double balance;
		private double credit;
		private double debit;
		@Column(name="date_time")
		private LocalDateTime dnt;
		private String status;
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "Acc_num")
		private CustomerInfoEntity customerEntity;
		
		
public CustomerInfoEntity getCustomerEntity() {
			return customerEntity;
		}
		public void setCustomerEntity(CustomerInfoEntity customerEntity) {
			this.customerEntity = customerEntity;
		}
		//		public Transactions() {
//			super();
			// TODO Auto-generated constructor stub
	//	}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public double getCredit() {
			return credit;
		}
		public void setCredit(double credit) {
			this.credit = credit;
		}
		public double getDebit() {
			return debit;
		}
		public void setDebit(double debit) {
			this.debit = debit;
		}
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public LocalDateTime getDnt() {
			return dnt;
		}
		public void setDnt(LocalDateTime dnt) {
			this.dnt = dnt;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
//		public CustomerInfo getCustomer() {
//			return customer;
//		}
//		public void setCustomer(CustomerInfo customer) {
//			this.customer = customer;
//		}
		
		
		

	}


