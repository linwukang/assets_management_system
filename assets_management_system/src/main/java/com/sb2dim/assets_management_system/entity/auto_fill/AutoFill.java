package com.sb2dim.assets_management_system.entity.auto_fill;

import com.sb2dim.assets_management_system.entity.util.EntityUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class AutoFill {
    public static void fill(Object entity) {
        metaInformation.forEach((annotationClass, fillMethod) -> {
            EntityUtil.getFieldsByAnnotation(entity, annotationClass).forEach((field) -> {
                try {
                    Annotation annotation = field.getAnnotation(annotationClass);
                    field.setAccessible(true);
                    Object fill = fillMethod.apply(entity, annotation);
                    field.set(entity, fill);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private static Map<Class<? extends Annotation>, BiFunction<Object, Annotation, Object>> metaInformation;
    static {
        List<Class<?>> classes =
                EntityUtil.findClasses("com.sb2dim.assets_management_system.entity.auto_fill.annotation");
        metaInformation = new HashMap<>();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotation()) {
                Class<? extends Annotation> annotation = (Class<? extends Annotation>)clazz;
                if (annotation.isAnnotationPresent(com.sb2dim.assets_management_system.entity.auto_fill.annotation.AutoFill.class)) {
                    System.out.println(annotation.getName());
                    com.sb2dim.assets_management_system.entity.auto_fill.annotation.AutoFill autoFill = annotation.getAnnotation(com.sb2dim.assets_management_system.entity.auto_fill.annotation.AutoFill.class);
                    Class<? extends BiFunction<Object, Annotation, Object>> fillMethodClass = autoFill.fillMethod();
                    BiFunction<Object, Annotation, Object> fillMethod = null;
                    try {
                        fillMethod = fillMethodClass.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    metaInformation.put(annotation, fillMethod);
                }
            }
        }
    }
}
