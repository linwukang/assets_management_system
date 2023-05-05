package com.sb2dim.assets_management_system.controller.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult {
    private Object data;
    private Integer code;
    private String message;

    public ResponseResult(Object data, Integer code) {
        this.data = data;
        this.code = code;
        this.message = null;
    }
}
