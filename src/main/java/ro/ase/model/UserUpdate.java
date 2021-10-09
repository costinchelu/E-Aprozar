package ro.ase.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ase.validator.PasswordMatches;

@Data
@NoArgsConstructor
@PasswordMatches(message="{update.password.matches}")
public class UserUpdate implements UserMatchingPassword{
	
	@NotNull(message="{update.surname.null}")
	@NotEmpty(message="{update.surname.empty}")
	private String surname;
	
	@NotNull(message="{update.name.null}")
	@NotEmpty(message="{update.name.empty}")
	private String name;
	
	@NotNull(message="{update.details.null}")
	@NotEmpty(message="{update.details.empty}")
	private String details;
	
	@Size(max = 24, min = 8, message="{update.password.size}")
	private String password;
	
	private String matchingPassword;
	
	@NotNull(message="{update.phone.null}")
	@NotEmpty(message="{update.phone.empty}")
	@Pattern(regexp="^[+]{1}\\d{11}$", message="{update.phone.invalid}")
	private String phone;
}
