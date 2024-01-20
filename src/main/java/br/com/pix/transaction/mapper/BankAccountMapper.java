package br.com.pix.transaction.mapper;

import java.time.LocalDate;

import br.com.pix.transaction.model.BankAccount;
import br.com.pix.transaction.model.dto.RequestBankAccountDTO;
import br.com.pix.transaction.model.dto.ResponseBankAccountDTO;

public class BankAccountMapper {
	
	public static BankAccount toModel(RequestBankAccountDTO requestBankAccountDTO) {
		return BankAccount.builder()
				.fullName(requestBankAccountDTO.getFullName())
				.agency(requestBankAccountDTO.getAgency())
				.account(requestBankAccountDTO.getAccount())
				.type(requestBankAccountDTO.getType())
				.phone(requestBankAccountDTO.getPhone())
				.email(requestBankAccountDTO.getEmail())
				.document(requestBankAccountDTO.getDocument())
				.bankBalance(requestBankAccountDTO.getBankBalance())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseBankAccountDTO modelToResponseBankAccountDTO(BankAccount bankAccount) {
		return ResponseBankAccountDTO.builder()
				.id(bankAccount.getId())
				.fullName(bankAccount.getFullName())
				.agency(bankAccount.getAgency())
				.account(bankAccount.getAccount())
				.type(bankAccount.getType())
				.bankBalance(bankAccount.getBankBalance())
				.build();
	}
	
	public static BankAccount deleteBankAccount(BankAccount bankAccount) {
		bankAccount.setActive(Boolean.FALSE);
		bankAccount.setChanged(LocalDate.now());
		return bankAccount;
	}
	
	public static BankAccount reactivateBankAccount(BankAccount bankAccount) {
		bankAccount.setActive(Boolean.TRUE);
		bankAccount.setChanged(LocalDate.now());
		return bankAccount;
	}
	
}
