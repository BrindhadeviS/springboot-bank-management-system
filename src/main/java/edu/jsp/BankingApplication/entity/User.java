package edu.jsp.BankingApplication.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@Email(message = "Enter valid  Email")
	@Column(unique = true, nullable = false)
	private String email;
	@NotBlank(message = "Password cannot be blank")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Loan> loans;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Account> accounts;

	public void addAccount(Account a) {
		accounts.add(a);
		a.setUser(this);
	}

	public void removeAccount(Account a) {
		accounts.remove(a);
		a.setUser(null);
	}

	public void addLoan(Loan loan) {
		loans.add(loan);
		loan.setUser(this);
	}

	public void removeLoan(Loan l) {
		loans.remove(l);
		l.setUser(null);
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
