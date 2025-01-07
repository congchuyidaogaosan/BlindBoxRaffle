package com.mysterybox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class FileConfig {
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    @PostConstruct
    public void init() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String[] subDirs = {"series", "styles", "avatars"};
        for (String dir : subDirs) {
            File subDir = new File(uploadPath + "/" + dir);
            if (!subDir.exists()) {
                subDir.mkdirs();
            }
        }
    }
} 