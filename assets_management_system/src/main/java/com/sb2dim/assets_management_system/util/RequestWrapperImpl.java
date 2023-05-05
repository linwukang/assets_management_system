package com.sb2dim.assets_management_system.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sb2dim.assets_management_system.entity.util.EntityUtil;
import com.sb2dim.assets_management_system.entity.util.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestWrapperImpl implements RequestWrapper {
    /*
    * 值             含义      参数个数
    * eq:           等于       2
    * ne:           不等于     2
    * gt:           大于       2
    * ge:           大于等于    2
    * lt:           小于       2
    * le:           小于等于    2
    * between       范围       3
    * isNull:       为空       1
    * isNotNull:    非空       1
    * */
    private String operator;
    private List<Object> values;

//    public <T> boolean into(QueryWrapper<T> wrapper) throws NoSuchFieldException, ClassNotFoundException {
//        // wrapper.getEntityClass() 为 null
//        String tableName = EntityUtil.getTableNameByEntityClass(wrapper.getEntityClass());
//        JoinColumn joinColumn = JoinColumn.getJoinColumn(tableName, (String) values.get(0));
//
//        String column = join(wrapper, joinColumn);
//        switch (operator) {
//            case "eq" -> wrapper.eq(column, values.get(1));
//            case "ne" -> wrapper.ne(column, values.get(1));
//            case "gt" -> wrapper.gt(column, values.get(1));
//            case "ge" -> wrapper.ge(column, values.get(1));
//            case "lt" -> wrapper.lt(column, values.get(1));
//            case "le" -> wrapper.le(column, values.get(1));
//            case "between" -> wrapper.between(column, values.get(1), values.get(2));
//            case "isNull" -> wrapper.isNull(column);
//            case "isNotNull" -> wrapper.isNotNull(column);
//            case "like" -> wrapper.like(column, values.get(1));
//            case "orderBy" -> wrapper.orderBy(true, (boolean) values.get(1), column);
//        }
//        return true;
//    }

    public Boolean judging(String entityClassName, Serializable id) {
        JoinColumn joinColumn = JoinColumn.getJoinColumn(entityClassName, (String) values.get(0));
        Object joinColumnValue = joinColumn.getValueById(id);

        BiFunction<String, String, Boolean> like = (str, likeExpr) -> {
            if (str == null) return false;
            // 转义特殊字符 %
            String regex = likeExpr
                    .replace("\\", "\\\\")
                    .replace("^", "\\^")
                    .replace("$", "\\$")
                    .replace(".", "\\.")
                    .replace("*", "\\*")
                    .replace("+", "\\+")
                    .replace("?", "\\?")
                    .replace("|", "\\|")
                    .replace("[", "\\[")
                    .replace("]", "\\]")
                    .replace("(", "\\(")
                    .replace(")", "\\)")
                    .replace("%", ".*")
                    .replace("_", ".")
                    .replace("\\\\%", "%")
                    .replace("\\\\_", "_");
            return Pattern.compile("^" + regex + "$").matcher(str).find();
        };
//        System.out.println("entityClassName: " + entityClassName);
//        System.out.println("id: " + id);
//        System.out.println("joinColumn: " + joinColumn);
//        System.out.println("joinColumnValue: " + joinColumnValue);
//        System.out.println("values.get(1): " + values.get(1).getClass().getName() + ":" + values.get(1));
//        System.out.println("joinColumnValue: " + joinColumnValue.getClass().getName() + ":" + joinColumnValue);
        return switch (operator) {
            case "eq" -> compare(joinColumnValue, values.get(1)) == 0;
            case "ne" -> compare(joinColumnValue, values.get(1)) != 0;
            case "gt" -> compare(joinColumnValue, values.get(1)) > 0;
            case "ge" -> compare(joinColumnValue, values.get(1)) >= 0;
            case "lt" -> compare(joinColumnValue, values.get(1)) < 0;
            case "le" -> compare(joinColumnValue, values.get(1)) <= 0;
            case "between" -> compare(joinColumnValue, values.get(1)) >= 0 && compare(joinColumnValue, values.get(2)) <= 0;
            case "isNull" -> joinColumnValue == null;
            case "isNotNull" -> joinColumnValue != null;
            case "like" -> like.apply((String)joinColumnValue, (String) values.get(1));
            default -> throw new IllegalArgumentException("");
        };
    }

    public static Integer compare(Object o1, Object o2) {
        if ((o1 instanceof Date && o2 instanceof String) || (o1 instanceof String && o2 instanceof Date)) { // 日期和字符串类型混合
            Date date1 = o1 instanceof Date ? (Date) o1 : Date.from(Instant.parse((String) o1));
            Date date2 = o2 instanceof Date ? (Date) o2 : Date.from(Instant.parse((String) o2));
            return date1.compareTo(date2);
        } else if (o1 instanceof Date && o2 instanceof Date) { // 两个日期类型
            return ((Date) o1).compareTo((Date) o2);
        }
        else { // 其他可比较类型
            return ((Comparable) o1).compareTo(o2);
        }
    }



//    public static <T> QueryWrapper<T> getQueryWrapper(List<WrapperRequest> wrappers) {
//        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
//        if (wrappers != null) {
//            wrappers.forEach((w) -> {
//                try {
//                    w.into(queryWrapper);
//                } catch (NoSuchFieldException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//        return queryWrapper;
//    }

    public static <E> String join(QueryWrapper<E> wrapper, JoinColumn joinColumn) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> entityClass = EntityUtil.getEntityClass(joinColumn.getEntityClassName());
        String joinTableName = EntityUtil.getTableNameByEntityClassName(joinColumn.getEntityClassName());
        String joinColumnName = EntityUtil.getColumnNameByField(entityClass.getDeclaredField(joinColumn.getFieldName()));
        String joinIdColumnName = EntityUtil.getIdColumnName(entityClass);

        if (joinColumn.getJoinId() != null) {
            wrapper.apply(joinTableName + "." + joinIdColumnName + "=" + join(wrapper, joinColumn.getJoinId()));
            join(wrapper, joinColumn.getJoinId());
        }

        return joinTableName + "." + joinColumnName;
    }
}
