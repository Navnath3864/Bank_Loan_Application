package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepository extends JpaRepository<CustomerLoanApplication, Integer>{

}
