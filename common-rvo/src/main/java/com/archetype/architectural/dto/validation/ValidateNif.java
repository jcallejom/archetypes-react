package com.archetype.architectural.dto.validation;


import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = NifConstraintValidator.class)
public @interface ValidateNif {
    String message() default "{message.nif.not.null}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}