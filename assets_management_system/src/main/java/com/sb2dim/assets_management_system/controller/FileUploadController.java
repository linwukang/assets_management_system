package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.controller.result.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    private static final String filePath = "D:\\project\\IDEA\\AssetsManagementSystem\\assets-management-system-web\\src\\assets\\user\\images\\";
    private static final String url = "\\src\\assets\\user\\images\\";

    @PostMapping("/images")
    @ResponseBody
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("上传失败，请选择文件");
            return new ResponseResult(null, Code.UPLOAD_ERR, "上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            System.out.println("上传成功");
            return new ResponseResult(url + fileName, Code.UPLOAD_OK, "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseResult(null, Code.UPLOAD_ERR, "上传失败");
    }
}
