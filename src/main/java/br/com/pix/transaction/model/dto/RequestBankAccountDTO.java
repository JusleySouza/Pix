package br.com.pix.transaction.model.dto;

import br.com.pix.transaction.enums.AccountType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class RequestBankAccountDTO {
	
	@NotEmpty(message = "{fullName.not.empty}")
	@Size(min = 2, max = 255, message = "{fullName.size}")
	private String fullName;
	@NotEmpty(message = "{agency.not.empty}")
	@Size(max = 4, message = "{agency.not.greater.than}")
	private String agency;
	@NotEmpty(message = "{account.not.empty}")
	@Size(max = 10, message = "{account.not.greater.than}")
	private String account;
	@Enumerated
	private AccountType type;
	@NotEmpty(message = "{phone.not.empty}")
	@Pattern(regexp = "(\\+)([0-9]{2})(\\([0-9]{2}\\))([9]{1})?([0-9]{4})-([0-9]{4})", message = "{phone.format}")
	private String phone;
	@Email(message = "{email.not.valid}")
	@NotEmpty(message = "{email.not.empty}")
	private String email;
	@NotEmpty(message = "{document.not.empty}")
	private String document;
	@NotNull(message = "{bankBalance.not.null}")
	private Double bankBalance;

}
