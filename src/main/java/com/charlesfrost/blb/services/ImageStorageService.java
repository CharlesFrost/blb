package com.charlesfrost.blb.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {
    public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + "/uploads/";
    private final Path fileStorageLocation;

    public ImageStorageService() {
        this.fileStorageLocation = Paths.get(UPLOAD_FOLDER).toAbsolutePath().normalize();
    }

    public String storeImage(MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(UPLOAD_FOLDER + multipartFile.getOriginalFilename());
        Files.write(path,bytes);
        return multipartFile.getOriginalFilename();
    }

    public Resource loadFileAsResource(String fileName) throws RuntimeException{
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
}
