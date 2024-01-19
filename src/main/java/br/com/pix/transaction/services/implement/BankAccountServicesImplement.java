package br.com.pix.transaction.services.implement;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.pix.transaction.config.LoggerConfig;
import br.com.pix.transaction.exception.DuplicateDocumentsException;
import br.com.pix.transaction.exception.ValidationException;
import br.com.pix.transaction.mapper.BankAccountMapper;
import br.com.pix.transaction.model.BankAccount;
import br.com.pix.transaction.model.dto.RequestBankAccountDTO;
import br.com.pix.transaction.model.dto.error.ResponseError;
import br.com.pix.transaction.repository.BankAccountRepository;
import br.com.pix.transaction.services.BankAccountServices;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class BankAccountServicesImplement implements BankAccountServices {
	
	@Autowired
	private BankAccountRepository repository;
	
	@Autowired
	private Validator validator;
	
	private BankAccount bankAccount;

	@Override
	public ResponseEntity<Object> create(RequestBankAccountDTO requestBankAccountDTO) {
		Set<ConstraintViolation<RequestBankAccountDTO>> violations = validator.validate(requestBankAccountDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_BANK_ACCOUNT.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		bankAccount = BankAccountMapper.toModel(requestBankAccountDTO);
		
		duplicateAccountValidator(bankAccount);
		
		duplicatePixKeysValidator(bankAccount);
		
		repository.save(bankAccount);
		
		LoggerConfig.LOGGER_BANK_ACCOUNT.info("Bank account salved successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	private void duplicateAccountValidator( BankAccount bankAccount) {
		BankAccount accountEntity = repository.findByAccountAndActiveTrue(bankAccount.getAccount());
		
		if(accountEntity != null) {
			throw new ValidationException("Unable to register bank account."
					+ " There is already a customer registered with this account. Please check and try again.");	
		}
	}
	
	private void duplicatePixKeysValidator( BankAccount bankAccount) {
		BankAccount keysEntity = repository.findByDocumentOrEmailOrPhoneAndActiveTrue(bankAccount.getDocument(), bankAccount.getEmail(), bankAccount.getPhone());
		
		if(keysEntity != null) {
			throw new DuplicateDocumentsException("Unable to register bank account."
					+ " There is already a customer registered with this pix key. Please check the document, email or phone number and try again.");	
		}
	}

}
