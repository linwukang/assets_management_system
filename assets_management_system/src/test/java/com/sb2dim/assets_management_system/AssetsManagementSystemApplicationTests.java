package com.sb2dim.assets_management_system;

//import com.sb2dim.assets_management_system.old.mapper.AssetsClassMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.entity.*;
import com.sb2dim.assets_management_system.entity.auto_fill.AutoFill;
import com.sb2dim.assets_management_system.entity.util.JoinColumn;
import com.sb2dim.assets_management_system.mapper.AssetsClassMapper;
//import com.sb2dim.assets_management_system.old.models.AssetsClass;
import com.sb2dim.assets_management_system.service.AssetsClassService;
import com.sb2dim.assets_management_system.service.AssetsStorageService;
import com.sb2dim.assets_management_system.entity.util.EntityUtil;
import com.sb2dim.assets_management_system.service.DepartmentService;
import com.sb2dim.assets_management_system.service.PersonnelService;
import com.sb2dim.assets_management_system.util.RequestWrapper;
import com.sb2dim.assets_management_system.util.RequestWrapperImpl;
import com.sb2dim.assets_management_system.validation.handler.UniqueHandler;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
class AssetsManagementSystemApplicationTests {
    @Autowired
    AssetsClassMapper assetsClassMapper;
    @Autowired
    AssetsClassService assetsClassService;

    @Autowired
    AssetsStorageService assetsStorageService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PersonnelService personnelService;

    @Test
    void test01() {
//        assetsClassService.list().forEach(System.out::println);
//        System.out.println("====================================");
//        System.out.println(assetsClassService.getById("ZCLB0003"));
//        System.out.println("====================================");
//        System.out.println(assetsClassService.removeById("ZCLB0005"));
//        System.out.println("====================================");
//        AssetsClass ac = assetsClassService.getById("ZCLB0003");
//        ac.setEnabled(true);
//        System.out.println(assetsClassService.saveOrUpdate(ac));
//        System.out.println("====================================");
        System.out.println("====================================");
        assetsClassService
                .page(new Page<AssetsClass>(1, 2))
                .getRecords()
                .forEach(System.out::println);
        System.out.println("====================================");
        assetsClassService
                .page(new Page<AssetsClass>(2, 2))
                .getRecords()
                .forEach(System.out::println);
    }

    @Autowired
    UniqueHandler uniqueHandler;
    @Test
    void test02() {
        uniqueHandler.getMetaInformation().forEach((a, b) -> {
            System.out.println(a.getName() + ": " );
            b.forEach((c, d) -> {
                System.out.println("\t" + c.getName() + ": " + d.message());
            });
        });
    }

    @Test
    void test03() throws ClassNotFoundException {
        IService<?> assetsBorrowService =
                EntityUtil.getServiceByEntityClassName(
                        "com.sb2dim.assets_management_system.entity",
                        "AssetsBorrow");
        System.out.println("assetsClassService: " + assetsClassService.getClass().getName());
        AssetsBorrow assetsBorrow = new AssetsBorrow();
        System.out.println("Table name of assetsBorrow: " + EntityUtil.getTableName(assetsBorrow));
        System.out.println("Table name of Department: " + EntityUtil.getTableNameByEntityClassName("Department"));
        System.out.println("Table name of AssetsClass: " + EntityUtil.getTableNameByEntityClass(AssetsClass.class));
        System.out.println("NotBlank field of assetsBorrow: ");
        EntityUtil.getFieldsByAnnotation(assetsBorrow, NotBlank.class).forEach(System.out::println);
    }

    @Test
    void test04() {
        AssetsStorage e = assetsStorageService.getById(1);
        System.out.println(e);
        Serializable value = EntityUtil.getJoinColumnValue(e, "assetName");
        System.out.println(value);
        value = EntityUtil.getJoinColumnValue(e, "Brand(brandId).name");
        System.out.println(value);
    }

    @Test
    void test05() {
        Department dept = new Department();
        dept.setProfile("aaaa");
        dept.setCode("bbb");
        dept.setName("ccc");
        System.out.println("dept: " + dept);
        AutoFill.fill(dept);
        System.out.println("full dept: " + dept);

        AssetsMaintain assetsMaintains = new AssetsMaintain();
        System.out.println("assetsMaintains: " + assetsMaintains);
        AutoFill.fill(assetsMaintains);
        System.out.println("full assetsMaintains: " + assetsMaintains);
    }

    @Test
    void test06() {
//        JoinColumn joinColumn = JoinColumn.getJoinColumn("A", "B(C(cid).bid).name");
//        // JoinColumn(joinId=JoinColumn(joinId=JoinColumn(joinId=null, entityClassName=A, fieldName=cid), entityClassName=C, fieldName=bid), entityClassName=B, fieldName=name)
//        System.out.println(joinColumn);
        JoinColumn joinColumn;

        joinColumn = JoinColumn.getJoinColumn("AssetsBorrow", "");
        System.out.println("资产借用id为1: " + joinColumn.getValueById(1));

        joinColumn = JoinColumn.getJoinColumn("AssetsBorrow", "Personnel(borrowerId).name");
        System.out.println("资产借用id为1的借用人的姓名: " + joinColumn.getValueById(1));

        joinColumn = JoinColumn.getJoinColumn("AssetsBorrow", "Department(Personnel(borrowerId).departmentId)");
        System.out.println("资产借用id为1的借用人的部门: " + joinColumn.getValueById(1));

        joinColumn = JoinColumn.getJoinColumn("AssetsBorrow", "Department(Personnel(borrowerId).departmentId).name");
        System.out.println("资产借用id为1的借用人的部门名: " + joinColumn.getValueById(1));
    }

    @Test
    void test07() {
        List<AssetsStorage> assetsStorageList = assetsStorageService.list();
        assetsStorageList.forEach(System.out::println);
        System.out.println("=======================================");
        System.out.println();
        List<RequestWrapper> wrappers = new ArrayList<>();
        wrappers.add(new RequestWrapperImpl("like", Arrays.asList("Supplier(supplierId).name", "%科技%")));

        List<AssetsStorage> assetsStorageList2 = RequestWrapper.getFilteredList(assetsStorageList, wrappers);
        assetsStorageList2.forEach(System.out::println);

    }

}
