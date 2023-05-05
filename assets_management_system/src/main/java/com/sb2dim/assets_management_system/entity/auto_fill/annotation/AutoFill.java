package com.sb2dim.assets_management_system.entity.auto_fill.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoFill {
    Class<? extends BiFunction<Object, Annotation, Object>> fillMethod();
}
