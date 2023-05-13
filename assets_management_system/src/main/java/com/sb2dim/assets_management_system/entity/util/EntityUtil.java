package com.sb2dim.assets_management_system.entity.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.ApplicationContextProvider;
import com.sb2dim.assets_management_system.entity.auto_fill.annotation.Now;
import org.springframework.core.ResolvableType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class EntityUtil {
    public static String entityClassPackage = "com.sb2dim.assets_management_system.entity";

    /**
     * 获取实体对象中拥有指定注解的字段
     * @param entity 实体对象
     * @param annotationType 注解类型
     * @return 拥有指定注解的字段列表
     * @param <E> 实体类
     */
    public static <E> List<Field> getFieldsByAnnotation(E entity, Class<? extends Annotation> annotationType) {
        return Arrays
                .stream(entity
                        .getClass()
                        .getDeclaredFields())
                .filter(field -> field.getAnnotation(annotationType) != null)
                .toList();
    }

    public static <E> void setDateToNow(E entity) {
        EntityUtil.getFieldsByAnnotation(entity, Now.class).forEach((field) -> {
            try {
                field.setAccessible(true);
                field.set(entity, new Date());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

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

    public static String getColumnNameByFieldName(Class<?> entityClass, String fieldName) throws NoSuchFieldException {
        return getColumnNameByField(entityClass.getDeclaredField(fieldName));
    }

    /**
     * 通过实体对象获取 TableId 列的字段
     *
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

    /**
     * 通过实体类获取 TableId 列的字段
     *
     * @param entityClass 实体类
     * @return TableId 列的字段
     */
    public static Field getIdField(Class<?> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableId.class)) {
                return field;
            }
        }
        throw new IllegalArgumentException("未找到实体类的 TableId 字段");
    }

    /**
     * 通过实体对象获取 id 值
     * @param entity 实体对象
     * @return id 值
     */
    public static Serializable getId(Object entity) {
        Field field = getIdField(entity);
        try {
            return (Serializable) getFieldValue(entity, field);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取主键的列名
     * @param entity 实体对象
     * @return 数据库主键列名
     */
    public static String getIdColumnName(Object entity) {
        return getColumnNameByField(getIdField(entity));
    }

    /**
     * 获取主键的列名
     * @param entityClass 实体类
     * @return 数据库主键列名
     */
    public static String getIdColumnName(Class<?> entityClass) {
        return getColumnNameByField(getIdField(entityClass));
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

    public static Serializable getJoinColumnValue(Object entity, String joinColumnName){
        // 人员管理(借用人id).name
        Pattern pattern =
                Pattern.compile(
                        "^(?<tableName>[A-z_][A-z\\d_]*)" +
                                "\\(" + "(?<id>[A-z_][A-z\\d_]*)" + "\\)" +
                                "\\." +
                                "(?<columnName>[A-z_][A-z\\d_]*)$"
                );
        try {
            Matcher matcher = pattern.matcher(joinColumnName);
            if (matcher.find()) {
                String tableName = matcher.group("tableName");
                String id = matcher.group("id");
                String columnName = matcher.group("columnName");

                IService<?> service = getServiceByEntityClassName(entityClassPackage, tableName);

                Serializable idValue = getJoinColumnValue(entity, id);
                Object innerEntity = service.getById(idValue);
                return getJoinColumnValue(innerEntity, columnName);
            }
            else {
                // joinColumnName 是当前主表中的字段
                // 通过字段名获取字段值
                Field joinField = entity.getClass().getDeclaredField(joinColumnName);
                joinField.setAccessible(true);
                return (Serializable) joinField.get(entity);
            }
        }
        catch (Exception e) {
            return null;
        }

    }

    /**
     * 扫描包下的所有类
     * @param packageName 包名
     * @return 类列表
     */
    public static List<Class<?>> findClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    for (File file : Objects.requireNonNull(directory.listFiles())) {
                        String filename = file.getName();
                        if (filename.endsWith(".class")) {
                            String className = packageName + '.' + filename.substring(0, filename.length() - 6);
                            Class<?> clazz = Class.forName(className);
                            classes.add(clazz);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 通过实体对象获取表名
     * @param entity 实体对象
     * @return 表名
     */
    public static String getTableName(Object entity) {
        TableName tableName = entity.getClass().getAnnotation(TableName.class);
        return tableName.value();
    }

    /**
     * 通过实体类获取表名
     * @param entityClass 实体类
     * @return 表名
     */
    public static String getTableNameByEntityClass(Class<?> entityClass){
        return entityClass.getAnnotation(TableName.class).value();
    }

    /**
     * 通过实体类名获取表名
     * @param entityClassName 实体类名
     * @return 表名
     * @throws ClassNotFoundException 在 <code>EntityUtil.entityClassPackage</code> 包下未发现该实体类
     */
    public static String getTableNameByEntityClassName(String entityClassName) throws ClassNotFoundException {
        Class<?> clazz = getEntityClass(entityClassName);
        return getTableNameByEntityClass(clazz);
    }

    public static Class<?> getEntityClass(String entityClassName) throws ClassNotFoundException {
        return Class.forName(entityClassPackage + "." + entityClassName);
    }

    /**
     * 通过实体对象和字段名获取字段值
     * @param entity 实体对象
     * @param fieldName 字段名
     * @return 字段值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object entity, String fieldName) {
        if (entity == null || fieldName == null || fieldName.equals("")) {
            return null;
        }
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            return getFieldValue(entity, field);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过实体对象和字段获取字段值
     * @param entity 实体对象
     * @param field 字段
     * @return 字段值
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object entity, Field field) throws IllegalAccessException {
        boolean canAccess = field.canAccess(entity);
        field.setAccessible(true);
        Object fieldValue = field.get(entity);
        field.setAccessible(canAccess);
        return fieldValue;
    }
}
