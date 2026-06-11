package edu.jsp.BankingApplication.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.Account;
import edu.jsp.BankingApplication.entity.Transaction;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.AccountRepository;
import edu.jsp.BankingApplication.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;

	public ResponseEntity<String> addTransaction(long accountId, Transaction transaction) {

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId", accountId));
		account.addTransaction(transaction);
		transactionRepository.save(transaction);
		return new ResponseEntity<>("transaction added", HttpStatus.CREATED);
	}

	public ResponseEntity<List<Transaction>> getAllTransactionByUser(long userId, int pageNo, int pageSize) {
		Sort sort = Sort.by("date").ascending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<Transaction> transactions = transactionRepository.findByAccountUserUid(userId, pageable);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	public ResponseEntity<List<Transaction>> getUsertransactionByAmount(long userId, Double st, Double end) {
		List<Transaction> transactions = transactionRepository.getUsertransactionByAmount(userId, st, end);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	public ResponseEntity<List<Transaction>> getUsertransactionByDate(long userId, LocalDateTime st, LocalDateTime end) {
		List<Transaction> transactions = transactionRepository.getUsertransactionByDate(userId, st, end);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

}
