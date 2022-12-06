package com.iqbalnetwork.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IFileUtils {
    void createFolder(String foldername);

    void createFile(MultipartFile file) throws Exception;
}
