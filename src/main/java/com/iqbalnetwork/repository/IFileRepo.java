package com.iqbalnetwork.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileRepo {
    Resource load(String filename);

    void store(MultipartFile file);
}
