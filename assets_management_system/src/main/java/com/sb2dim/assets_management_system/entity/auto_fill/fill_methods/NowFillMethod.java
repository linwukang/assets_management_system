package com.sb2dim.assets_management_system.entity.auto_fill.fill_methods;


import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.function.BiFunction;

public class NowFillMethod implements BiFunction<Object, Annotation, Object> {
    @Override
    public Object apply(Object entity, Annotation annotation) {
        return new Date();
    }
}
