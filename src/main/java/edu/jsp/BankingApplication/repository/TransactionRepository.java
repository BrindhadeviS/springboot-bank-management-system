package edu.jsp.BankingApplication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.jsp.BankingApplication.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	
	
	List<Transaction>   findByAccountUserUid(long userId,Pageable pageable);
	
	@Query(value = "select t from Transaction  t where t.account.user.uid = ?1 and t.date between ?2 and ?3")
	List<Transaction>   getUsertransactionByDate(long userId, LocalDateTime st,LocalDateTime end);

	@Query(value = "select t from Transaction  t where t.account.user.uid = ?1 and t.amount between ?2 and ?3")
	List<Transaction>   getUsertransactionByAmount(long userId, Double st,Double end);

}
