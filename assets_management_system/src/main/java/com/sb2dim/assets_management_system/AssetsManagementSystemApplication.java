package com.sb2dim.assets_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication
//@MapperScan("com.sb2dim.assets_management_system.old.mapper")
//@MapperScan("com.sb2dim.assets_management_system.mapper")
public class AssetsManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetsManagementSystemApplication.class, args);
    }

}
