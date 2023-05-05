package com.sb2dim.assets_management_system.validation.validator;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.ApplicationContextProvider;
import com.sb2dim.assets_management_system.service.ServiceUtil;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    private IService service;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        service = (IService) ApplicationContextProvider.getApplicationContext().getBean(constraintAnnotation.service());
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(fieldName, value);
        return service.count(queryWrapper) == 0;
    }
}