package com.iqbalnetwork.models.request;

import com.iqbalnetwork.models.CourseInfo;
import com.iqbalnetwork.models.CourseType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CourseDto {

    @NotBlank(message = "{invalid.title.required}")
    private String title;
    private String description;
    @NotBlank(message = "Link is required")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String link;
    private CourseType courseType;
    private CourseInfo courseInfo;
    private String fileName;
    private String fileUrl;
}
