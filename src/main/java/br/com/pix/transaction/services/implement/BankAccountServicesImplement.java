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
		
		duplicateDocumentValidator(bankAccount);
		
		duplicateEmailValidator(bankAccount);
		
		duplicatePhoneValidator(bankAccount);
		
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
	
	private void duplicateDocumentValidator( BankAccount bankAccount) {
		BankAccount documentEntity = repository.findByDocumentAndActiveTrue(bankAccount.getDocument());
		
		if(documentEntity != null) {
			throw new DuplicateDocumentsException("Unable to register bank account."
					+ " There is already a customer registered with this document. Please check and try again.");	
		}
	}
	
	private void duplicateEmailValidator( BankAccount bankAccount) {
		BankAccount emailEntity = repository.findByEmailAndActiveTrue(bankAccount.getEmail());
		
		if(emailEntity != null) {
			throw new ValidationException("Unable to register bank account."
					+ " There is already a customer registered with this email. Please check and try again.");	
		}
	}
	
	private void duplicatePhoneValidator( BankAccount bankAccount) {
		BankAccount phoneEntity = repository.findByPhoneAndActiveTrue(bankAccount.getPhone());
		
		if(phoneEntity != null) {
			throw new ValidationException("Unable to register bank account."
					+ " There is already a customer registered with this phone. Please check and try again.");	
		}
	}

}
