package com.iqbalnetwork.models.request;

import com.iqbalnetwork.utils.constant.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseReqWithFile extends FormDataWithFileReq {
    private String title;
    private String description;
    private String link;
    private String courseTypeId;
    private Integer courseDuration;
    private Level courseLevel;
}
