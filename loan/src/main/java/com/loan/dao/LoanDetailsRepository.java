package com.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.LoanDetails;
@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {

}
