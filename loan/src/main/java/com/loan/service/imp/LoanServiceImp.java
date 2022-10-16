package com.loan.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dao.LoanDetailsRepository;
import com.loan.dao.PaymentScheduleRepository;
import com.loan.entity.LoanDetails;
import com.loan.entity.PaymentSchedule;
import com.loan.exceptions.ResourceNotFoundException;
import com.loan.service.LoanService;
import com.loan.utill.PaymentStatus;

@Service
public class LoanServiceImp implements LoanService {

	@Autowired
	private LoanDetailsRepository loanDetailsRepo;

	@Autowired
	private PaymentScheduleRepository paymentScheduleRepo;

	// To Create a New Loan
	@Override
	public String createLoan(LoanDetails loanDetails) {

		PaymentSchedule schedule = null;
		// To get current Date
		Calendar loanStartDate = Calendar.getInstance();
		loanStartDate.setTime(loanDetails.getLoanStartDate());
		
		Calendar maturityDate = Calendar.getInstance();
		maturityDate.setTime(loanDetails.getMaturityDate());
		
		int loanYears = maturityDate.get(Calendar.YEAR) - loanStartDate.get(Calendar.YEAR);

		int paymentFrequency = loanDetails.getPaymentFrequency();
		long loanAmount = loanDetails.getLoanAmount();
		double projectedInterest = ((loanDetails.getInterestRate() * loanAmount) / 100) * loanYears;
		int totalPaymentFrequency = (12 * loanYears) / paymentFrequency;
		double paymentAmount = projectedInterest / (totalPaymentFrequency);
		long loanAmountPerFrequency = loanAmount / totalPaymentFrequency;

		List<PaymentSchedule> paymentScheduleList = loanDetails.getPaymentSchedule();
		// To create PaymentSchedule Based on Payment Term
		while (loanDetails.getMaturityDate().compareTo(loanStartDate.getTime()) > 0) {
			if (loanDetails.getPaymentTerm().equalsIgnoreCase("Even Principal")) {
				loanStartDate.add(Calendar.MONTH, paymentFrequency);
				paymentAmount = loanAmountPerFrequency + projectedInterest;
				schedule = new PaymentSchedule(loanStartDate.getTime(), loanAmount, projectedInterest,
						PaymentStatus.PROJECTED.toString(), paymentAmount);
				paymentScheduleList.add(schedule);
				loanAmount = loanAmount - loanAmountPerFrequency;
				projectedInterest = ((loanDetails.getInterestRate() * loanAmount) / 100) * loanYears;

			} else if (loanDetails.getPaymentTerm().equalsIgnoreCase("Interest Only")) {
				loanStartDate.add(Calendar.MONTH, paymentFrequency);
				if (loanDetails.getMaturityDate().compareTo(loanStartDate.getTime()) == 0) {
					paymentAmount = loanAmount + (projectedInterest / totalPaymentFrequency);
				}
				schedule = new PaymentSchedule(loanStartDate.getTime(), loanAmount, projectedInterest,
						PaymentStatus.PROJECTED.toString(), paymentAmount);
				paymentScheduleList.add(schedule);
			}
		}
		loanDetails.setPaymentSchedule(paymentScheduleList);
		loanDetailsRepo.save(loanDetails);
		return "Loan is Successfully Created...";
	}

	// To Get the Created Loan list and Loan Schedule..
	@Override
	public List<LoanDetails> loanList() {
		List<LoanDetails> loanList = this.loanDetailsRepo.findAll();
		return loanList;
	}

	// To get the Loan List and Loan Schedule by customer Id also Converting the
	// Payment Status to AwaitingPayment by comparing the current date..
	@Override
	public LoanDetails loanListById(int customerId) {
		// current Date
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(date);
		LoanDetails loanList = loanDetailsRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Id Not Found with Id : "+customerId));
		List<PaymentSchedule> paymentScheduleList = loanList.getPaymentSchedule();
		for (PaymentSchedule paymentSchedule : paymentScheduleList) {
			String paymentScheduleDate = dateFormat.format(paymentSchedule.getPaymentDate());
			if (paymentScheduleDate.equals(currentDate) && paymentSchedule.getPaymentStatus().equals("PROJECTED")) {
				paymentSchedule.setPaymentStatus(PaymentStatus.AWAITINGPAYMENT.toString());
			}
		}
		loanDetailsRepo.save(loanList);
		return loanDetailsRepo.findById(customerId).get();
	}
	
	// To update the Payment Status(PAID)..
	@Override
	public String paymentStatus(int paymentId) {
		PaymentSchedule scheduleList = paymentScheduleRepo.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("Payment Id Not Found with Id : "+paymentId));
		scheduleList.setPaymentStatus(PaymentStatus.PAID.toString());
		paymentScheduleRepo.save(scheduleList);
		return "Payment Paid Successfully...";
	}

}
