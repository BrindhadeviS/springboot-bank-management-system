package edu.jsp.BankingApplication.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.BankingApplication.entity.Account;
import edu.jsp.BankingApplication.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountContoller {

	@Autowired
	private AccountService accountService;
	
	
	@PostMapping("/addAccount/user/{userId}")
	public  ResponseEntity<String> addAccount(@PathVariable long userId,@RequestBody Account account) {
		return accountService.createAccount(userId, account);
	}
	
}
