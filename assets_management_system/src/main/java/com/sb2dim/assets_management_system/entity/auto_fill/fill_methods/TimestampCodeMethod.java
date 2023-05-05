package com.sb2dim.assets_management_system.entity.auto_fill.fill_methods;

import com.sb2dim.assets_management_system.entity.auto_fill.annotation.TimestampCode;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;

public class TimestampCodeMethod implements BiFunction<Object, Annotation, Object> {
    @Override
    public Object apply(Object entity, Annotation annotation) {
        TimestampCode timestampCode = (TimestampCode) annotation;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(now);
        return timestampCode.prefix() + timestamp;
    }
}
