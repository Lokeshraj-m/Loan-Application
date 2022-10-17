package com.loan.service;

import java.util.List;

import com.loan.entity.LoanDetails;
                  
public interface LoanService {
	
	public String createLoan(LoanDetails loanDetails);

	public List<LoanDetails> loanList();

	public LoanDetails loanListById(int customerId);

	public String paymentStatus(int paymentId);

}
