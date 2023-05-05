package com.sb2dim.assets_management_system.entity.auto_fill.annotation;

import com.sb2dim.assets_management_system.entity.auto_fill.fill_methods.TimestampCodeMethod;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoFill(fillMethod = TimestampCodeMethod.class)
public @interface TimestampCode {
    String prefix();
}
