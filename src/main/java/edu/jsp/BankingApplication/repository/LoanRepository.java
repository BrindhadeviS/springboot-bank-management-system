package edu.jsp.BankingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.BankingApplication.entity.Loan;

public interface LoanRepository  extends  JpaRepository<Loan, Long>{

	List<Loan>   findByUserUid(long uid);
	
}
