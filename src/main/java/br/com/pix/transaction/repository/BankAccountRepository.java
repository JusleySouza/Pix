package br.com.pix.transaction.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pix.transaction.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
	
	BankAccount findByDocumentAndActiveTrue(String document);

}
