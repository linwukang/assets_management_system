import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/asset_management", "root", "root")
//                .globalConfig(builder -> {
//                    builder.author("sb2dim") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
////                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D://B"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.sb2dim.assets_management_system") // 设置父包名
////                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://B")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder
//                            .addInclude("assets_borrow")
//                            .addInclude("assets_class")
//                            .addInclude("assets_inventory")
//                            .addInclude("assets_inventory_sheet")
//                            .addInclude("assets_maintain")
//                            .addInclude("assets_purchase_requisition")
//                            .addInclude("assets_scrap")
//                            .addInclude("assets_storage")
//                            .addInclude("assets_transfer")
//                            .addInclude("brand")
//                            .addInclude("department")
//                            .addInclude("equipment_use")
//                            .addInclude("gaining_method")
//                            .addInclude("personal_info")
//                            .addInclude("personnel")
//                            .addInclude("scrap_mode")
//                            .addInclude("storage_place")
//                            .addInclude("supplier")
//                            .addInclude("user_info")      // 设置需要生成的表名
////                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
//                    ;
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/assets_management_test", "root", "root")
                .globalConfig(builder -> {
                    builder.author("perfect imitator") // 设置作者 baomidou 默认值:作者
                            .enableSwagger() // 开启 swagger 模式 默认值:false
                            .fileOverride() // 覆盖已生成文件 默认值:false
                            .disableOpenDir()//禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd")// 注释日期
                            .dateType(DateType.ONLY_DATE)//定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir("D:\\C"); // 指定输出目录 /opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
                    //System.getProperty("user.dir")为你当前模块的绝对路径
                })

                .packageConfig(builder -> {
                    builder.parent("com.sb2dim.assets_management_system.test") // 父包模块名 默认值:com.baomidou
                            .controller("controller")//Controller 包名 默认值:controller
                            .entity("entity")//Entity 包名 默认值:entity
                            .service("service")//Service 包名 默认值:service
                            .mapper("mapper")//Mapper 包名 默认值:mapper
//                            .moduleName("educms") // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+ "/src/main/resources/test/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })

                .strategyConfig(builder -> {
                    builder.addInclude("assets_borrow")
                            .addInclude("assets_class")
                            .addInclude("assets_inventory")
                            .addInclude("assets_inventory_sheet")
                            .addInclude("assets_maintain")
                            .addInclude("assets_purchase_requisition")
                            .addInclude("assets_scrap")
                            .addInclude("assets_storage")
                            .addInclude("assets_transfer")
                            .addInclude("brand")
                            .addInclude("department")
                            .addInclude("equipment_use")
                            .addInclude("gaining_method")
                            .addInclude("personal_info")
                            .addInclude("personnel")
                            .addInclude("scrap_mode")
                            .addInclude("storage_place")
                            .addInclude("supplier")
                            .addInclude("user_info")      // 设置需要生成的表名
//                            .addTablePrefix("tb_", "c_") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()// 实体类策略配置
                            .idType(IdType.ASSIGN_ID)//主键策略  雪花算法自动生成的id
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT)) // 自动填充配置
                            .addTableFills(new Property("gmtModified", FieldFill.INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            .logicDeleteColumnName("deleted")// 说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
