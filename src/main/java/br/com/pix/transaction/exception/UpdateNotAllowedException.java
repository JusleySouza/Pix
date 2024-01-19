package br.com.pix.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UpdateNotAllowedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UpdateNotAllowedException(String exception) {
		super(exception);
	}
}
