package com.iqbalnetwork.models.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public abstract class FormDataWithFileReq {

    protected MultipartFile file;
}