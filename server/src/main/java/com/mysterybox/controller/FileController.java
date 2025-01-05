package com.mysterybox.controller;

import com.mysterybox.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileController {
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    @Value("${file.access.path}")
    private String accessPath;
    
    @PostMapping
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(400, "文件不能为空");
        }
        
        String fileName = UUID.randomUUID().toString() + getFileExtension(file.getOriginalFilename());
        File dest = new File(uploadPath + fileName);
        
        try {
            file.transferTo(dest);
            return Result.success("上传成功", accessPath + fileName);
        } catch (IOException e) {
            return Result.error(500, "上传失败");
        }
    }
    
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
} 