package com.iqbalnetwork.models.request;

import com.iqbalnetwork.utils.Level;
import lombok.Data;

@Data
public class CourseReqWithFile extends FormDataWithFileReq {
    private String title;
    private String description;
    private String link;
    private String courseTypeId;
    private Integer courseDuration;
    private Level courseLevel;
}
