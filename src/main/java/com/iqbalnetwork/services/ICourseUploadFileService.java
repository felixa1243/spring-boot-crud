package com.iqbalnetwork.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ICourseUploadFileService {
    void upload(MultipartFile file);
    Resource download(String filename);
}
