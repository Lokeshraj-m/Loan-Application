package com.loan.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.loan.dao.LoanDetailsRepository;
import com.loan.entity.LoanDetails;
import com.loan.entity.PaymentSchedule;
import com.loan.utill.PaymentStatus;

@Component
public class Scheduler {

	@Autowired
	LoanDetailsRepository loanDetailsRepo;
	
	@Scheduled(cron = "0/1 * * * * *")
	public void changeAwaitingStatus() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(date);
		List<LoanDetails> loanList = this.loanDetailsRepo.findAll();
		for (LoanDetails loanDetails : loanList) {
			List<PaymentSchedule> paymentScheduleList = loanDetails.getPaymentSchedule();
			for (PaymentSchedule paymentSchedule : paymentScheduleList) {
				String paymentScheduleDate = dateFormat.format(paymentSchedule.getPaymentDate());
				if (paymentScheduleDate.equals(currentDate) && paymentSchedule.getPaymentStatus().equals("PROJECTED")) {
					paymentSchedule.setPaymentStatus(PaymentStatus.AWAITINGPAYMENT.toString());
					loanDetailsRepo.save(loanDetails);
				}
			}
		}
	}
}
