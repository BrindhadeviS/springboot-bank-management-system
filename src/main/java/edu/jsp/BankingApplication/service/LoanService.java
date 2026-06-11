package edu.jsp.BankingApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.Loan;
import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.LoanRepository;
import edu.jsp.BankingApplication.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class LoanService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Transactional
	public ResponseEntity<String> applyLoan(long userId, Loan loan) {
		User u = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User", "User Id  = ", userId));
		u.addLoan(loan);
		userRepository.save(u);
		return  new ResponseEntity<String>("Laon applied",HttpStatus.CREATED);
	}

	public ResponseEntity<Loan> getLaonById(Long id) {
		Loan  l  =  loanRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan id", id));
	
		return new ResponseEntity<Loan>(l,HttpStatus.OK);
	}

	public ResponseEntity<String> deleteLoan(Long userId, Long loanId) {
		User u = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User", "User Id  = ", userId));
		Loan l = loanRepository.findById(loanId)
				.orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan id", loanId));

		u.removeLoan(l);
		loanRepository.delete(l);
		return new ResponseEntity<String>("Loan deleted",HttpStatus.OK);
	}

	public  ResponseEntity<List<Loan>> getLoanByUserId(long userId) {
		List<Loan> list  =loanRepository.findByUserUid(userId);
		 
		 return  new ResponseEntity<List<Loan>>(list,HttpStatus.OK);
		 
		 
	}

	public String getLoanStatus(long id) {
		Loan l = loanRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Loan", "Loan id", id));

		return l.getStatus();
	}

}
