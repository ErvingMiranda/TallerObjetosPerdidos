package org.Ezone.POO.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidadorJakarta {
    static {
        Logger.getLogger("org.hibernate.validator").setLevel(Level.OFF);
    }

    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    private ValidadorJakarta() {
    }

    public static <T> void validar(T objeto) {
        Set<ConstraintViolation<T>> errores = VALIDATOR.validate(objeto);

        if (!errores.isEmpty()) {
            StringBuilder mensaje = new StringBuilder();

            for (ConstraintViolation<T> error : errores) {
                mensaje.append(error.getMessage()).append("\n");
            }

            throw new IllegalArgumentException(mensaje.toString().trim());
        }
    }

    public static <T> void validarPropiedad(T objeto, String propiedad) {
        Set<ConstraintViolation<T>> errores = VALIDATOR.validateProperty(objeto, propiedad);

        if (!errores.isEmpty()) {
            StringBuilder mensaje = new StringBuilder();

            for (ConstraintViolation<T> error : errores) {
                mensaje.append(error.getMessage()).append("\n");
            }

            throw new IllegalArgumentException(mensaje.toString().trim());
        }
    }
}