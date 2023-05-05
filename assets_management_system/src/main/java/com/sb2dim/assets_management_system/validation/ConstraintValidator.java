package com.sb2dim.assets_management_system.validation;

import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public interface ConstraintValidator<A extends Annotation, T> {
    default void initialize(A constraintAnnotation) {
    }

    boolean isValid(T var1, ConstraintValidatorContext var2);
}
