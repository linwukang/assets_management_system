package com.sb2dim.assets_management_system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.controller.result.ResponseResult;
import com.sb2dim.assets_management_system.entity.AssetsInventory;
import com.sb2dim.assets_management_system.entity.AssetsInventorySheet;
import com.sb2dim.assets_management_system.service.AssetsInventorySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 资产盘点 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/assets-inventory")
public class AssetsInventoryController extends BaseController<AssetsInventory> {
    @Autowired
    AssetsInventorySheetService assetInventorySheetService;


    /**
     * 结束盘点
     * @param id 资产盘点 id
     * @return
     */
    @GetMapping("/can-close/{id}")
    public ResponseResult canCloseInventory(@PathVariable Integer id) {
        AssetsInventorySheet assetInventorySheet = new AssetsInventorySheet();
        assetInventorySheet.setAssetsInventoryId(id);
        assetInventorySheet.setIsInventoried(false);
        QueryWrapper<AssetsInventorySheet> wrapper = new QueryWrapper<>(assetInventorySheet);
        long count = assetInventorySheetService.count(wrapper);
        if (count == 0) {
            service.update();
            return new ResponseResult(null, Code.QUERY_OK);
        }
        else {
            return new ResponseResult(count, Code.QUERY_ERR, "仍有未录入盘点结果的资产，您不能结束盘点！");
        }
    }

    @PutMapping("/close/{id}")
    public ResponseResult closeInventory(@PathVariable Integer id, @RequestBody Date endDate) {
        ResponseResult canClose = canCloseInventory(id);
        if (Code.QUERY_OK.equals(canClose.getCode())) {
            AssetsInventory assetInventory = new AssetsInventory();
            assetInventory.setId(id);
            assetInventory.setEndDate(endDate);
            boolean save = service.save(assetInventory);
            if (save) {
                return new ResponseResult(id, Code.UPDATE_OK);
            }
            else {
                return new ResponseResult(id, Code.UPDATE_ERR, "服务器繁忙，请稍后重试！");
            }
        }
        else {
            return canClose;
        }
    }

}
