package com.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.PaymentSchedule;
@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Integer> {

}
