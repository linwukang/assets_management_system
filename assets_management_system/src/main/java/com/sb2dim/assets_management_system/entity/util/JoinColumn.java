package com.sb2dim.assets_management_system.entity.util;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.service.ServiceUtil;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinColumn {
    // id 列名，可以是 JoinColumn 类型或者 null
    @Nullable
    private JoinColumn joinId;
    // 上下文中当前的表名
    private String entityClassName;
    // 列名
    @Nullable
    private String fieldName;

    public static JoinColumn getJoinColumn(String primaryTableEntityClassName, String joinColumnName) {
        // 人员管理(借用人id).name
        Pattern tableNameAndIdAndColumnName =
                Pattern.compile(
                        "^(?<tableName>[A-z_][A-z\\d_]*)" +
                                "\\(" + "(?<id>.*)" + "\\)" +
                                "\\." +
                                "(?<filedName>[A-z_][A-z\\d_]*)$"
                        );
        Pattern tableNameAndId =
                Pattern.compile(
                        "^(?<tableName>[A-z_][A-z\\d_]*)" +
                                "\\(" + "(?<id>.*)" + "\\)"
                );
        try {
            Matcher matcher = tableNameAndIdAndColumnName.matcher(joinColumnName);
            if (matcher.find()) {
                String tableName = matcher.group("tableName");
                String id = matcher.group("id");
                String filedName = matcher.group("filedName");
                JoinColumn joinColumn = new JoinColumn();

                JoinColumn idValue = getJoinColumn(primaryTableEntityClassName, id);
                joinColumn.setJoinId(idValue);
                joinColumn.setEntityClassName(tableName);
                joinColumn.setFieldName(filedName);

                return new JoinColumn(idValue, tableName, filedName);
            } else {
                matcher = tableNameAndId.matcher(joinColumnName);
                if (matcher.find()) {
                    String tableName = matcher.group("tableName");
                    String id = matcher.group("id");
                    JoinColumn idValue = getJoinColumn(primaryTableEntityClassName, id);

                    return new JoinColumn(idValue, tableName, null);
                }
                else {
                    return new JoinColumn(null, primaryTableEntityClassName, "".equals(joinColumnName) ? null : joinColumnName);
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public Object getValueById(Serializable id) {
        IService<?> service;
        try {
            service = ServiceUtil.getServiceByEntityClassName(EntityUtil.entityClassPackage, entityClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object entity =
                joinId == null
                        ? service.getById(id)
                        : service.getById((Serializable) joinId.getValueById(id));

        return (fieldName == null) ? entity : EntityUtil.getFieldValue(entity, fieldName);
    }
}
