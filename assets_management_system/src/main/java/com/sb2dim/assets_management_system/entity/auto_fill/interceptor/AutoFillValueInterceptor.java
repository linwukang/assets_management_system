package com.sb2dim.assets_management_system.entity.auto_fill.interceptor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sb2dim.assets_management_system.entity.auto_fill.annotation.AutoFill;
import com.sb2dim.assets_management_system.util.Entity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;

@Component
public class AutoFillValueInterceptor implements HandlerInterceptor {
//    @Autowired
//    private Map<Class<? extends Annotation>, Function<Object, Object>> metaInformation;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 在请求处理之前进行拦截器的处理逻辑
//        return true; // 返回 false 表示请求被拦截，不会继续进行处理；返回 true 则表示请求经过拦截器后可以正常处理
//    }
//    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
//        Object[] args = invocation.getArgs();
//        for (Object arg : args) {
//            if (arg != null && arg.getClass().isAnnotationPresent(TableName.class)) {
//                // 获取实体对象
//                Field[] fields = arg.getClass().getDeclaredFields();
//                for (Field field : fields) {
//                    // 如果字段带有  注解，则设置当前时间
//                    metaInformation.forEach((annotation, fillMethod) -> {
//                        if (field.isAnnotationPresent(annotation)) {
//                            field.setAccessible(true);
//                            try {
//                                field.set(arg, fillMethod.apply(arg));
//                            } catch (IllegalAccessException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    });
//                }
//            }
//        }
//        return invocation.proceed();
//    }
//
//    @Bean(name = "AutoFillValueInterceptorInit")
//    private static Map<Class<? extends Annotation>, Function<Object, Object>> init()
//            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        List<Class<?>> classes =
//                Entity.findClasses("com.sb2dim.assets_management_system.entity.auto_fill.annotation");
//        Map<Class<? extends Annotation>, Function<Object, Object>> metainformation = new HashMap<>();
//
//        for (Class<?> clazz : classes) {
//            if (clazz.isAnnotation()) {
//                Class<? extends Annotation> annotation = (Class<? extends Annotation>)clazz;
//                if (annotation.isAnnotationPresent(AutoFill.class)) {
//                    AutoFill autoFill = annotation.getAnnotation(AutoFill.class);
//                    Class<? extends Function<Object, Object>> fillMethodClass = autoFill.fillMethod();
//                    Function<Object, Object> fillMethod = fillMethodClass.getDeclaredConstructor().newInstance();
//                    metainformation.put(annotation, fillMethod);
//                }
//            }
//        }
//        return metainformation;
//    }
}
