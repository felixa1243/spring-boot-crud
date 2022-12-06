package com.iqbalnetwork.services;

import com.iqbalnetwork.repository.IFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseUploadFileService implements ICourseUploadFileService {
    private final IFileRepo fileRepo;

    public CourseUploadFileService(@Autowired IFileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    @Override
    public void upload(MultipartFile file) {
        fileRepo.store(file);
    }

    @Override
    public Resource download(String filename) {
        return fileRepo.load(filename);
    }
}
