package br.com.pix.transaction.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pix.transaction.model.dto.RequestBankAccountDTO;
import br.com.pix.transaction.model.dto.ResponseBankAccountDTO;
import br.com.pix.transaction.services.BankAccountServices;

@RestController
@RequestMapping("/account")
public class BankAccountController {
	
	@Autowired
	private BankAccountServices services;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestBankAccountDTO requestBankAccountDTO){
		return services.create(requestBankAccountDTO);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ResponseBankAccountDTO>> findAll(){
		return new ResponseEntity<List<ResponseBankAccountDTO>>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/list/inactive")
	public ResponseEntity<List<ResponseBankAccountDTO>> findAllInactive(){
		return new ResponseEntity<List<ResponseBankAccountDTO>>(services.findAllInactive(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseEntity<Object> delete(@PathVariable("accountId") UUID accountId){
		services.delete(accountId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{accountId}")
	public ResponseEntity<Object> reactivate(@PathVariable("accountId") UUID accountId){
		services.reactivateAccount(accountId);
		return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
	}
	
}
