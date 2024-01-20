package br.com.pix.transaction.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pix.transaction.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
	
	BankAccount findByAccountAndActiveTrue(String account);
	
	BankAccount findByDocumentOrEmailOrPhoneAndActiveTrue(String document, String email, String phone);
	
	List<BankAccount> findAllByActiveTrue();
	
	List<BankAccount> findAllByActiveFalse();
	
}
