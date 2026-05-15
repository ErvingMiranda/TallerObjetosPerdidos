package org.Ezone.POO.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.Ezone.POO.annotation.TextoLimpio;

public class ValidarTextoLimpio implements ConstraintValidator<TextoLimpio, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        for (char caracter : value.toCharArray()) {
            if (!Character.isLetter(caracter) && !Character.isSpaceChar(caracter)) {
                return false;
            }
        }

        return true;
    }
}