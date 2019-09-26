package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.models.ResponseBody;
import com.charlesfrost.blb.services.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private ImageStorageService imageStorageService;

    public ImageController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/image")
    public ResponseEntity upload(@RequestParam("image") MultipartFile multipartFile) {
        String imageName=null;
        if (multipartFile==null) {
            return ResponseEntity.badRequest().body(new ResponseBody("Prosze wysłać obrazek",null));
        }
        try {
            imageName = imageStorageService.storeImage(multipartFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseBody("Błąd przy zapisywaniu obrazka", multipartFile.getOriginalFilename()));
        }
        return ResponseEntity.ok(imageName);
    }


}

