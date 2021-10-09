package ro.ase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ro.ase.model.UserMatchingPassword;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserMatchingPassword user = (UserMatchingPassword) value;
        boolean match = user.getPassword().equals(user.getMatchingPassword());
        if (!match) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Cele doua campuri de parola nu sunt identice.").addPropertyNode("matchingPassword").addConstraintViolation();
        }
        return match;
    }
}
