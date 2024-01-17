package br.com.pix.transaction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pix.transaction.services.implement.BankAccountServicesImplement;
import lombok.Generated;

@Generated
public class LoggerConfig {
	
	public static final Logger LOGGER_BANK_ACCOUNT = LoggerFactory.getLogger(BankAccountServicesImplement.class);
	
}
