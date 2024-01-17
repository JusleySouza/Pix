package br.com.pix.transaction.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pix.transaction.model.dto.RequestBankAccountDTO;

@Service
public interface BankAccountServices {
	
	public ResponseEntity<Object> create(RequestBankAccountDTO requestBankAccountDTO);

}
