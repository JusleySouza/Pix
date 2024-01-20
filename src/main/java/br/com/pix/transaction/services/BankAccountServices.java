package br.com.pix.transaction.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pix.transaction.model.BankAccount;
import br.com.pix.transaction.model.dto.RequestBankAccountDTO;
import br.com.pix.transaction.model.dto.ResponseBankAccountDTO;

@Service
public interface BankAccountServices {
	
	public ResponseEntity<Object> create(RequestBankAccountDTO requestBankAccountDTO);
	
	public List<ResponseBankAccountDTO>findAll();
	
	public List<ResponseBankAccountDTO>findAllInactive();
	
	public BankAccount delete(UUID accountId);
	
	public ResponseEntity<ResponseBankAccountDTO> reactivateAccount(UUID accountId);

}
