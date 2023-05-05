package com.sb2dim.assets_management_system.entity.auto_fill.annotation;

import com.sb2dim.assets_management_system.entity.auto_fill.fill_methods.NowFillMethod;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoFill(fillMethod = NowFillMethod.class)
public @interface Now {
}
