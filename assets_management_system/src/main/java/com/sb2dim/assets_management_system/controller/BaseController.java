package com.sb2dim.assets_management_system.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.controller.result.ResponseResult;
import com.sb2dim.assets_management_system.entity.auto_fill.AutoFill;
import com.sb2dim.assets_management_system.service.ServiceUtil;
import com.sb2dim.assets_management_system.entity.util.EntityUtil;
import com.sb2dim.assets_management_system.util.RequestWrapper;
import com.sb2dim.assets_management_system.util.RequestWrapperImpl;
import com.sb2dim.assets_management_system.validation.handler.UniqueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController<E extends Serializable> {
    @Autowired
    private IService<E> service;

    @Autowired
    private UniqueHandler uniqueHandler;

    /**
     * 获取实体类数据表中所有的数据
     * @param conditions 从前端传回的筛选条件
     * @return 包含所有行的数据列表
     */
    @GetMapping
    public ResponseResult list(@RequestParam(required = false) List<RequestWrapper> conditions) {
        List<E> entities = service.list();
        entities = RequestWrapper.getFilteredList(entities, conditions);

        return new ResponseResult(
                entities,
                entities != null ? Code.QUERY_ALL_OK : Code.QUERY_ALL_ERR,
                entities != null ? null : "服务器繁忙，请稍后重试！");
    }

    /**
     * 通过 requestBody 中的 dataStruct 查询所有符合条件的数据
     * @param requestBody 请求体
     * @return Map 类型的数据列表
     */
    @PostMapping("/join")
    public ResponseResult joinList(@RequestBody Map<String, Object> requestBody) {
        List<String> dataStruct = (List<String>) requestBody.get("dataStruct");
        List<RequestWrapper> conditions = (requestBody.get("conditions") == null)
                ? null
                : ((List<? extends Map<String, ?>>) requestBody.get("conditions"))
                .stream()
                .map(RequestWrapper::mapToRequestWrapper)
                .toList();

        List<E> rawEntities = service.list();
        rawEntities = RequestWrapper.getFilteredList(rawEntities, conditions);

        List<Map<String, Object>> entities = null;
        if (rawEntities != null) {
            entities = rawEntities
                    .stream()
                    .map((e) -> {
                        Map<String, Object> entityMap = new HashMap<>();
                        BeanMap.create(e).forEach((k, v) -> entityMap.put((String) k, v));
                        dataStruct.forEach(ds -> entityMap.put(ds, ServiceUtil.getJoinColumnValue(e, ds)));
                        return entityMap;
                    })
                    .toList();
        }
        return new ResponseResult(
                entities,
                entities != null ? Code.QUERY_ALL_OK : Code.QUERY_ALL_ERR,
                entities != null ? null : "服务器繁忙，请稍后重试！");
    }

    /**
     * 通过 requestBody 中的 dataStruct 查询指定 id 符合条件的数据
     * @param requestBody 请求体
     * @return Map 类型的数据
     */
    @PostMapping("/join/{id}")
    public ResponseResult joinById(@RequestBody Map<String, Object> requestBody, @PathVariable String id) {
        List<String> dataStruct = (List<String>) requestBody.get("dataStruct");
        E rawEntity = service.getById(id);
        Map<String, Object> entity = null;

        if (rawEntity != null) {
            entity = new HashMap<>();
            BeanMap rawEntityMap = BeanMap.create(rawEntity);
            for (Object k : rawEntityMap.keySet()) {
                entity.put((String) k, rawEntityMap.get(k));
            }
            for (String ds : dataStruct) {
                entity.put(ds, ServiceUtil.getJoinColumnValue(rawEntity, ds));
            }
        }

        return new ResponseResult(
                entity,
                entity != null ? Code.QUERY_OK : Code.QUERY_ERR,
                entity != null ? null : "服务器繁忙，请稍后重试！");
    }


    @PostMapping("/join/page/{page}/{size}")
    public ResponseResult join_page_size(@PathVariable int page,
                                         @PathVariable int size,
                                         @RequestBody Map<String, Object> requestBody) {
        List<String> dataStruct = (List<String>) requestBody.get("dataStruct");
        List<RequestWrapper> conditions = (requestBody.get("conditions") == null)
                ? null
                : ((List<? extends Map<String, ?>>) requestBody.get("conditions"))
                .stream()
                .map(RequestWrapper::mapToRequestWrapper)
                .toList();


//        Page<E> p = new Page<E>(page, size);
//        List<E> rawEntities = service.page(p).getRecords();
        List<E> rawEntities = RequestWrapper.getFilteredList(service.list(), conditions);

        List<Map<String, Object>> entities = null;
        if (rawEntities != null) {
            entities = rawEntities
                    .stream()
                    .map((e) -> {
                        Map<String, Object> entityMap = new HashMap<>();
                        (BeanMap.create(e)).forEach((k, v) -> entityMap.put((String) k, v));
                        dataStruct.forEach(ds -> entityMap.put(ds, ServiceUtil.getJoinColumnValue(e, ds)));
                        return entityMap;
                    })
                    .toList();
        }
        entities = RequestWrapper.getPage(entities, page, size);
        return new ResponseResult(
                entities,
                entities != null ? Code.QUERY_PAGE_OK : Code.QUERY_PAGE_ERR,
                entities != null ? null : "服务器繁忙，请稍后重试！");
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseResult page_size(@PathVariable int page,
                                    @PathVariable int size,
                                    @RequestParam(required = false) List<RequestWrapper> conditions) {
        Page<E> p = new Page<E>(page, size);
        List<E> entities = service.page(p).getRecords();
        entities = RequestWrapper.getFilteredList(entities, conditions);

        return new ResponseResult(
                entities,
                entities != null ? Code.QUERY_PAGE_OK : Code.QUERY_PAGE_ERR,
                entities != null ? null : "服务器繁忙，请稍后重试！");
    }

    @GetMapping("/{id}")
    public ResponseResult selectById(@PathVariable Serializable id) {
        E entity = service.getById(id);
        return new ResponseResult(
                entity,
                entity != null ? Code.QUERY_OK : Code.QUERY_ERR,
                entity != null ? null : "服务器繁忙，请稍后重试！");
    }

    @GetMapping("/{id}/field/{field}")
    public ResponseResult selectFieldById(@PathVariable Serializable id, @PathVariable String field) throws NoSuchFieldException, IllegalAccessException {
        E entity = service.getById(id);
        if (entity == null) {
            return new ResponseResult(
                    null,
                    Code.QUERY_ERR,
                    "服务器繁忙，请稍后重试！");
        }
        Field entityField = entity.getClass().getDeclaredField(field);
        entityField.setAccessible(true);
        Serializable fieldValue = (Serializable) entityField.get(entity);
        return new ResponseResult(
                fieldValue,
                fieldValue != null ? Code.QUERY_OK : Code.QUERY_ERR,
                fieldValue != null ? null : "服务器繁忙，请稍后重试！");
    }

    @PostMapping
    public ResponseResult create(@Validated @RequestBody E entity, BindingResult bindingResult) {
        String message = null;
        AutoFill.fill(entity);

        if (bindingResult.hasErrors()) {
            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            // 保存失败
            return new ResponseResult(false, Code.CREATE_ERR, message);
        }
        
        List<String> uniqueMessages = uniqueHandler.createValid(entity);

        if (uniqueMessages != null && uniqueMessages.size() > 0) {
            return new ResponseResult(false, Code.CREATE_ERR, uniqueMessages.get(0));
        } else {
            try {
                boolean succeeded = service.save(entity);
                // 保存成功
                return new ResponseResult(succeeded, succeeded ? Code.CREATE_OK : Code.CREATE_ERR, null);
            }
            catch (DuplicateKeyException e) {
                // 保存失败
                return new ResponseResult(false, Code.CREATE_ERR, "服务器繁忙，请稍后重试！");
            }
        }
    }

    @PutMapping
    public ResponseResult update(@Validated @RequestBody E entity, BindingResult bindingResult) {
        System.out.println(entity);
        String message = null;
        List<String> uniqueMessages = uniqueHandler.updateValid(entity);
        if (uniqueMessages != null && uniqueMessages.size() > 0) {
            return new ResponseResult(false, Code.CREATE_ERR, uniqueMessages.get(0));
        }
        try {
            boolean succeeded = service.saveOrUpdate(entity);
            return new ResponseResult(
                    succeeded,
                    succeeded ? Code.UPDATE_OK : Code.UPDATE_ERR,
                    succeeded ? null : "保存失败，请稍后重试！");
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
            // 保存失败
            return new ResponseResult(
                    false,
                    Code.UPDATE_ERR,
                    "服务器繁忙，请稍后重试！");
        }

    }

    @PutMapping("/{id}")
    public ResponseResult updateById(
            @PathVariable Serializable id,
            @Validated @RequestBody E entity,
            BindingResult bindingResult) throws IllegalAccessException {
        System.out.println(id);
        System.out.println(entity);

        String message = null;
        List<String> uniqueMessages = uniqueHandler.updateByIdValid(id, entity);
        if (uniqueMessages != null && uniqueMessages.size() > 0) {
            return new ResponseResult(false, Code.CREATE_ERR, uniqueMessages.get(0));
        }
        try {
            UpdateWrapper<E> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(EntityUtil.getIdColumnName(entity), id);
//            Field idField = Entity.getIdField(entity);
//            idField.setAccessible(true);
//            updateWrapper.set(Entity.getColumnName(entity), idField.get(entity));

            boolean succeeded = service.update(entity, updateWrapper);
            return new ResponseResult(
                    succeeded,
                    succeeded ? Code.UPDATE_OK : Code.UPDATE_ERR,
                    succeeded ? null : "保存失败，请稍后重试！");
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
            // 保存失败
            return new ResponseResult(
                    false,
                    Code.UPDATE_ERR,
                    "服务器繁忙，请稍后重试！");
        }

    }

    @PutMapping("/{id}/{name}/{value}")
    public ResponseResult updateColumnById(
            @PathVariable Serializable id,
            @PathVariable String name,
            @PathVariable Object value) {
        E entity = service.getById(id);
        UpdateWrapper<E> updateWrapper = new UpdateWrapper<>(entity);
        updateWrapper.set(name, value);

        boolean succeeded = service.update(updateWrapper);
        return new ResponseResult(
                succeeded,
                succeeded ? Code.UPDATE_OK : Code.UPDATE_ERR,
                succeeded ? null : "修改失败");
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable Serializable id) {
        boolean succeeded = service.removeById(id);
        return new ResponseResult(
                succeeded,
                succeeded ? Code.DELETE_OK : Code.DELETE_ERR,
                succeeded ? null : "服务器繁忙，请稍后重试！");
    }

    @PostMapping("/count")
    public ResponseResult count(@RequestBody Map<String, Object> requestBody) {
        List<RequestWrapper> conditions = (requestBody.get("conditions") == null)
                ? null
                : ((List<? extends Map<String, ?>>) requestBody.get("conditions"))
                .stream()
                .map(RequestWrapper::mapToRequestWrapper)
                .toList();

//        if (conditions == null) {
//            return new ResponseResult(
//                    service.count(WrapperRequest.getQueryWrapper(conditions)),
//                    Code.QUERY_OK
//            );
//        }
//        else {
        List<E> entities = service.list(/*WrapperRequest.getQueryWrapper(conditions)*/);
        entities = RequestWrapper.getFilteredList(entities, conditions);
        return new ResponseResult(
                entities.size(),
                Code.QUERY_OK
        );
//        }
    }
}
