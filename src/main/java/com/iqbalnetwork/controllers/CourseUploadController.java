package com.iqbalnetwork.controllers;

import com.iqbalnetwork.models.request.FormDataWithFileReq;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.services.ICourseUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class CourseUploadController {
    @Autowired
    ICourseUploadFileService service;

    @PostMapping("upload")
    public ResponseEntity upload(FormDataWithFileReq files) throws Exception {
        MultipartFile file = files.getFile();
        service.upload(file);
        return ResponseEntity.status(201).body(new SuccessResponse<>("success upload file", file.getOriginalFilename()));
    }

    @GetMapping(value = "download", params = {"filename"})
    public ResponseEntity download(@RequestParam String filename) throws Exception {
        Resource file = service.download(filename);
        return ResponseEntity.status(200).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFilename())
                .body(file);
    }
}
