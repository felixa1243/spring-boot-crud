package com.iqbalnetwork.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseRequest {
    private String title;
    private String description;
    private String link;
}
