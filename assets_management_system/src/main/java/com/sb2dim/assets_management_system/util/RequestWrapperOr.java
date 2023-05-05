package com.sb2dim.assets_management_system.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class RequestWrapperOr implements RequestWrapper {
    private RequestWrapper left;
    private RequestWrapper right;
    public RequestWrapperOr(RequestWrapper rw1, RequestWrapper rw2) {
        left = rw1;
        right = rw2;
    }

    @Override
    public Boolean judging(String entityClassName, Serializable id) {
        return left.judging(entityClassName, id) || right.judging(entityClassName, id);
    }
}
