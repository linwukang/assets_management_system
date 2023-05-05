package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.Brand;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController<Brand> {
//    @Autowired
//    private BrandService brandService;
//
//    @GetMapping("/list")
//    public List<Brand> list() {
//        return brandService.list();
//    }
//
//    @GetMapping("/page/{page}")
//    public List<Brand> page(@PathVariable int page) {
//        Page<Brand> p = new Page<Brand>(page, 15);
//        return brandService.page(p).getRecords();
//    }
//
//    @GetMapping("/page/{page}/{size}")
//    public List<Brand> page_size(@PathVariable int page, @PathVariable int size) {
//        Page<Brand> p = new Page<Brand>(page, size);
//        return brandService.page(p).getRecords();
//    }
//
//    @GetMapping("/select/{id}")
//    public Brand selectById(@PathVariable Serializable id) {
//        return brandService.getById(id);
//    }
//
//    @PostMapping("/create")
//    public boolean create(@ModelAttribute Brand brand) {
//        return brandService.save(brand);
//    }
//
//    @PostMapping("/update")
//    public boolean update(@ModelAttribute Brand brand) {
//        return brandService.update(brand, null);
//    }
//
//    @GetMapping("/delete/{id}")
//    public boolean deleteById(@PathVariable Serializable id) {
//        return brandService.removeById(id);
//    }
}
