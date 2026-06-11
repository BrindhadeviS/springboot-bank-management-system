package edu.jsp.BankingApplication.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.BankingApplication.entity.Loan;
import edu.jsp.BankingApplication.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanContoller {
	
	
	@Autowired
	private  LoanService loanService;

	
	@PostMapping("/applyloan/{userId}")
	public ResponseEntity<String> applyLoan(@PathVariable long userId,@RequestBody Loan loan) {
		return  loanService.applyLoan(userId, loan);
	}

	@GetMapping("/getLoanById/{id}")
	public ResponseEntity<Loan> getLaonById(@PathVariable Long id) {
		return  loanService.getLaonById(id);
	}

	//http://localhost:8080/deleteLaonById/user/1/loan/1
	@DeleteMapping("/deleteLaonById/user/{userId}/loan/{loanId}")
	public ResponseEntity<String> deleteLoan(@PathVariable Long userId,@PathVariable Long loanId) {
		return  loanService.deleteLoan(userId, loanId);
	}

	@GetMapping("/getLoanByUserId/{userId}")
	public ResponseEntity<List<Loan>> getLoanByUserId(@PathVariable long userId) {
		return  loanService.getLoanByUserId(userId);
	}

	@GetMapping("/getLoanStatus/{id}")
	public String getLoanStatus(@PathVariable long id) {
		return loanService.getLoanStatus(id);
	}
	
	
}
