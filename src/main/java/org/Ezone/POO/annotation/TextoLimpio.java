package org.Ezone.POO.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.Ezone.POO.validator.ValidarTextoLimpio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidarTextoLimpio.class)
public @interface TextoLimpio {
    String message() default "El texto solo debe contener letras y espacios.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}