package br.com.pix.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
