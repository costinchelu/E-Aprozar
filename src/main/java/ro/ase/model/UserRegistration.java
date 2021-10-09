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
@PasswordMatches(message = "{register.password.matches}")
public class UserRegistration implements UserMatchingPassword {

    @NotNull(message = "{register.name.null}")
    @NotEmpty(message = "{register.name.empty}")
    private String name;

    @NotNull(message = "{register.email.null}")
    @NotEmpty(message = "{register.email.empty}")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{register.email.invalid}")
    private String email;

    @Size(max = 24, min = 8, message = "{register.password.size}")
    private String password;

    private String matchingPassword;
}
