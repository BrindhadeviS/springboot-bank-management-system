package edu.jsp.BankingApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.BankingApplication.entity.Account;
import edu.jsp.BankingApplication.entity.User;
import edu.jsp.BankingApplication.exception.ResourcesNotFoundException;
import edu.jsp.BankingApplication.repository.AccountRepository;
import edu.jsp.BankingApplication.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> createAccount(long userId, Account account) {
		User u = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User", "userId", userId));

		u.addAccount(account);
		userRepository.save(u);
		return new ResponseEntity<String>("Account created", HttpStatus.CREATED);
	}

	public ResponseEntity<Account> getAccountById(long accountId) {
		Account a = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId", accountId));
		return new ResponseEntity<Account>(a, HttpStatus.OK);
	}

	public ResponseEntity<String> deteleAccountById(long userId, long accountId) {
		User u = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User", "userId", userId));
		Account a = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId", accountId));
		u.removeAccount(a);
		accountRepository.delete(a);
		return new ResponseEntity<String>("Account deleted", HttpStatus.OK);
	}

	public ResponseEntity<List<Account>> getAccountByUserId(long userId) {
		List<Account> list = accountRepository.getAccountaByUserid(userId);
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	public ResponseEntity<Double> getBalance(long accountId) {
		Account a = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourcesNotFoundException("Account", "AccountId", accountId));
		return  new ResponseEntity<Double>(a.getBalance(),HttpStatus.OK);
	}

}
