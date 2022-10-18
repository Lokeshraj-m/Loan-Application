package com.loan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class LoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	@Pattern(regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,}$", message = "Invalid Customer Name.. "
			+ "Enter only Letters and Spaces..."
			+ "Example: Lokesh Raj M")
	@NotEmpty(message="Must not be Empty")
	private String customerName;
	@Min(value = 1000, message = "Inavlid Amount...Enter above 1000...")
	private long loanAmount;
	@CreationTimestamp
	private Date tradeDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date loanStartDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date maturityDate;
	private int paymentFrequency;
	@Range(min = (long) 0.0, max = (long) 100.0,message="Invalid Interest Rate... Interest Rate Should be Between 0 to 100... ")
	private double interestRate;
	private String paymentTerm;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "paymentIdFk",referencedColumnName = "customerId")
	private List<PaymentSchedule> paymentSchedule = new ArrayList<>();
	
	
	public LoanDetails() {
		super();
	}


	public LoanDetails(int customerId, String customerName, long loanAmount, Date tradeDate, Date loanStartDate,
			Date maturityDate, int paymentFrequency, double interestRate, String paymentTerm,
			List<PaymentSchedule> paymentSchedule) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.loanAmount = loanAmount;
		this.tradeDate = tradeDate;
		this.loanStartDate = loanStartDate;
		this.maturityDate = maturityDate;
		this.paymentFrequency = paymentFrequency;
		this.interestRate = interestRate;
		this.paymentTerm = paymentTerm;
		this.paymentSchedule = paymentSchedule;
	}




	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public List<PaymentSchedule> getPaymentSchedule() {
		return paymentSchedule;
	}


	public void setPaymentSchedule(List<PaymentSchedule> paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}


	public String getPaymentTerm() {
		return paymentTerm;
	}


	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public long getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}


	public Date getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}


	public Date getLoanStartDate() {
		return loanStartDate;
	}


	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}


	public Date getMaturityDate() {
		return maturityDate;
	}


	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}


	public int getPaymentFrequency() {
		return paymentFrequency;
	}


	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	@Override
	public String toString() {
		return "LoanDetails [customerId=" + customerId + ", loanAmount=" + loanAmount + ", tradeDate=" + tradeDate
				+ ", loanStartDate=" + loanStartDate + ", maturityDate=" + maturityDate + ", paymentFrequency="
				+ paymentFrequency + ", interestRate=" + interestRate + "]";
	}
}
