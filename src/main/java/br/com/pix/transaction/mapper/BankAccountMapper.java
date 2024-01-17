package br.com.pix.transaction.mapper;

import java.time.LocalDate;

import br.com.pix.transaction.model.BankAccount;
import br.com.pix.transaction.model.dto.RequestBankAccountDTO;

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
		
}
