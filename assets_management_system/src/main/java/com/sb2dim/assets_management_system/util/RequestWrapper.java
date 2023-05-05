package com.sb2dim.assets_management_system.util;

import com.sb2dim.assets_management_system.entity.util.EntityUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface RequestWrapper {
    Boolean judging(String entityClassName, Serializable id);
    static <E> List<E> getFilteredList(List<E> data, List<RequestWrapper> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            return data;
        }
        else {
            return data
                    .stream()
                    .filter((e) -> {
                        for (RequestWrapper condition : conditions) {
                            if (!condition.judging(e.getClass().getSimpleName(), EntityUtil.getId(e))) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .toList();
        }
    }

    /**
     * 根据页码和页宽，返回对应的List
     *
     * @param list     原始List
     * @param pageNum  页码（从1开始）
     * @param pageSize 页宽
     * @return 对应页码的List
     */
    static <T> List<T> getPage(List<T> list, int pageNum, int pageSize) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        int total = list.size();
        int pageCount = (total + pageSize - 1) / pageSize; // 总页数

        if (pageNum < 1 || pageNum > pageCount) {
            return null;
        }

        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);

        return list.subList(fromIndex, toIndex);
    }

    static RequestWrapper mapToRequestWrapper(Map<String, ? extends Object> map) {
        if (map.get("left") != null && map.get("right") != null) {
            Object left = map.get("left");
            Object right = map.get("right");
            if (left instanceof Map) {
                left = mapToRequestWrapper((Map<String, ? extends Object>) left);
            }
            if (right instanceof Map) {
                right = mapToRequestWrapper((Map<String, ? extends Object>) right);
            }

            return new RequestWrapperOr((RequestWrapper) left, (RequestWrapper) right);
        }
        else {
            return new RequestWrapperImpl((String) map.get("operator"), (List<Object>) map.get("values"));
        }
    }
}
