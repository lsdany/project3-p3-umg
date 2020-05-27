package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.services.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@Controller @Slf4j
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload-file")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file){
        try {
            InputStream is = file.getInputStream();
            uploadService.readJsonFile(is);
        } catch (IOException e) {
            log.error("Error obtaining file from html ",e);
        }
        return "upload";
    }

}
