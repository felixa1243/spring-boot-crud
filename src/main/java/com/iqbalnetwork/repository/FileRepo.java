package com.iqbalnetwork.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileRepo implements IFileRepo {
    private final Path root;

    public FileRepo(@Value("${upload.path}") String rootPath) {
        this.root = Paths.get(rootPath);
    }
    @Override
    public void store(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (!filename.matches(".*\\.png|jpeg|jpg|bmp$")) {
                throw new RuntimeException("file type is not supported");
            }

            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));

        } catch (Exception err) {
            throw new RuntimeException("Couldn't store the file. Error: " + err.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Couldn't read the file");
            }
        } catch (Exception err) {
            throw new RuntimeException("Error " + err.getMessage());
        }
    }
}
