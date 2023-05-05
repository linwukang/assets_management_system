package com.sb2dim.assets_management_system.validation.handler;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.ApplicationContextProvider;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

@Component
public class UniqueHandler {
    @Value("com.sb2dim.assets_management_system.entity")
    private String packageScan;

    public String getPackageScan() {
        return packageScan;
    }

    public void setPackageScan(String packageScan) {
        this.packageScan = packageScan;
    }

    public Map<Class<?>, Map<Field, Unique>> getMetaInformation() {
        return metaInformation;
    }

    @Autowired
    private Map<Class<?>, Map<Field, Unique>> metaInformation;

    public UniqueHandler() {
    }

    public List<String> createValid(Object entity) {
        Map<Field, Unique> fieldUniqueMap = metaInformation.get(entity.getClass());
        if (fieldUniqueMap == null) {
            return new ArrayList<>();
        }

        List<String> messages = new ArrayList<>();

        fieldUniqueMap.forEach((field, unique) -> {
            field.setAccessible(true);
            if (!verification(entity, field, unique)) {
                messages.add(unique.message());
            }
        });

        return messages;
    }

    public List<String> updateValid(Object entity) {
        Map<Field, Unique> fieldUniqueMap = metaInformation.get(entity.getClass());
        if (fieldUniqueMap == null) {
            return new ArrayList<>();
        }

        List<String> messages = new ArrayList<>();
        Field idField = getIdField(entity);
        idField.setAccessible(true);
        fieldUniqueMap.forEach((field, unique) -> {
            field.setAccessible(true);
            try {
                if (!updateByIdVerification(entity, field, unique, (Serializable) idField.get(entity))) {
                    messages.add(unique.message());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return messages;
    }

    public List<String> updateByIdValid(Serializable id, Object entity) {
        Map<Field, Unique> fieldUniqueMap = metaInformation.get(entity.getClass());
        if (fieldUniqueMap == null) {
            return new ArrayList<>();
        }

        List<String> messages = new ArrayList<>();
        Field idField = getIdField(entity);
        fieldUniqueMap.forEach((field, unique) -> {
            field.setAccessible(true);
            if (!updateByIdVerification(entity, field, unique, id)) {
                messages.add(unique.message());
            }
        });

        return messages;
    }

    /**
     * 校验单个字段是否重复
     * @param entity 实体类
     * @param field 字段
     * @param unique Unique 注解
     * @return 是否重复，重复则返回 false，否则返回 true
     */
    private static boolean verification(Object entity, Field field, Unique unique) {
        try {
            Object fieldValue = field.get(entity);
            IService<Object> service =
                    (IService<Object>) ApplicationContextProvider
                        .getApplicationContext()
                        .getBean(unique.service());

            QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(getColumnNameByField(field), fieldValue);
            return service.count(queryWrapper) == 0;

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean updateByIdVerification(Object entity, Field field, Unique unique, Serializable id) {
        try {
            Object fieldValue = field.get(entity);
            IService<Object> service =
                    (IService<Object>) ApplicationContextProvider
                            .getApplicationContext()
                            .getBean(unique.service());

            QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(getColumnNameByField(field), fieldValue);
            queryWrapper.ne(getColumnNameByField(getIdField(entity)), id);
            return service.count(queryWrapper) == 0;

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 通过字段获取数据库表的列名
     * @param field 实体类的字段
     * @return 数据表列名
     */
    private static String getColumnNameByField(Field field) {
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
    private static Field getIdField(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableId.class)) {
                return field;
            }
        }
        throw new IllegalArgumentException("未找到实体类的 TableId 字段");
    }

    @Bean
    private static Map<Class<?>, Map<Field, Unique>> init() {
        List<Class<?>> classes = findClasses("com.sb2dim.assets_management_system.entity");
        Map<Class<?>, Map<Field, Unique>> metainformation = new HashMap<>();

        for (Class<?> clazz : classes) {
            metainformation.put(clazz, new HashMap<>());

            Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Unique.class))
                    .forEach(field -> {
                        metainformation.get(clazz).put(field, field.getAnnotation(Unique.class));
                    });
        }

        return metainformation;
    }

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

}
