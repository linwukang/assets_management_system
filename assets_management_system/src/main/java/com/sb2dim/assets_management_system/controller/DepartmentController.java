package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.Department;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController<Department> {
//    @Autowired
//    private DepartmentService departmentService;
//
//    @GetMapping("/list")
//    public List<Department> list() {
//        return departmentService.list();
//    }
//
//    @GetMapping("/page/{page}")
//    public List<Department> page(@PathVariable int page) {
//        Page<Department> p = new Page<Department>(page, 15);
//        return departmentService.page(p).getRecords();
//    }
//
//    @GetMapping("/page/{page}/{size}")
//    public List<Department> page_size(@PathVariable int page, @PathVariable int size) {
//        Page<Department> p = new Page<Department>(page, size);
//        return departmentService.page(p).getRecords();
//    }
//
//    @GetMapping("/select/{id}")
//    public Department selectById(@PathVariable Serializable id) {
//        return departmentService.getById(id);
//    }
//
//    @PostMapping("/create")
//    public boolean create(@ModelAttribute Department department) {
//        return departmentService.save(department);
//    }
//
//    @PostMapping("/update")
//    public boolean update(@ModelAttribute Department department) {
//        return departmentService.update(department, null);
//    }
//
//    @GetMapping("/delete/{id}")
//    public boolean deleteById(@PathVariable Serializable id) {
//        return departmentService.removeById(id);
//    }
}
