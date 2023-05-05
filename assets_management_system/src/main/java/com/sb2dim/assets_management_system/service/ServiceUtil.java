package com.sb2dim.assets_management_system.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.ApplicationContextProvider;
import com.sb2dim.assets_management_system.entity.util.EntityUtil;
import com.sb2dim.assets_management_system.entity.util.JoinColumn;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class ServiceUtil<E> {
    static final String entityClassPackage = "com.sb2dim.assets_management_system.entity";

    /**
     * 通过字段获取数据库表的列名
     * @param field 实体类的字段
     * @return 数据表列名
     */
    public static String getColumnNameByField(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null) {
            return tableField.value();
        }
        else {
            TableId tableId = field.getAnnotation(TableId.class);
            return tableId.value();
        }
    }

    /**
     * 通过实体类对象获取 TableId 列的字段
     * @param entity 实体类对象
     * @return TableId 列的字段
     */
    public static Field getIdField(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableId.class)) {
                return field;
            }
        }
        throw new IllegalArgumentException("未找到实体类的 TableId 字段");
    }

    public static String getIdColumnName(Object entity) {
        return getColumnNameByField(getIdField(entity));
    }

    /**
     * 通过实体类的类名获取对应的 Service
     * @param packageName 包名
     * @param className 实体类名
     * @return 实体类对应的 Service
     */
    public static IService<?> getServiceByEntityClassName(String packageName, String className) throws ClassNotFoundException {
        Class<?> entityClass = Class.forName(packageName + "." + className);
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(IService.class, entityClass);
        return (IService<?>) ApplicationContextProvider
                .getApplicationContext()
                .getBeanProvider(resolvableType)
                .getIfAvailable();
    }

    public static Serializable getJoinColumnValue(Object entity, String joinColumnName) {
        String primaryTableEntityClassName = entity.getClass().getSimpleName();
        JoinColumn joinColumn = JoinColumn.getJoinColumn(primaryTableEntityClassName, joinColumnName == null ? "" : joinColumnName);
        return (Serializable) joinColumn.getValueById(EntityUtil.getId(entity));
    }
}
