package com.sb2dim.assets_management_system.validation.constraints;

import com.sb2dim.assets_management_system.validation.validator.UniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.io.Serializable;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 唯一约束
 * 该注解标注的字段的值在指定表中唯一
 */
@Target({FIELD, TYPE})
@Retention(RUNTIME)
@Constraint(
        validatedBy = {UniqueValidator.class}
)
@Repeatable(Unique.List.class)
@Documented
public @interface Unique {
    String message() default "...";

    Class<?> service();

    String fieldName();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({FIELD, TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Unique[] value();
    }
}
