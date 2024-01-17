package br.com.pix.transaction.model.dto;

import java.util.UUID;

import br.com.pix.transaction.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBankAccountDTO {
	
	private UUID id;
	private String fullName;
	private String agency;
	private String account;
	private AccountType type;
	private Double bankBalance;

}
