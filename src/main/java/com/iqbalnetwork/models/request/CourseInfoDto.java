package com.iqbalnetwork.models.request;

import com.iqbalnetwork.utils.constant.Level;
import lombok.Data;

@Data
public class CourseInfoDto {
    private Integer duration;
    private Level level;
}
