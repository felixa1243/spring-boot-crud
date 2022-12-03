package com.iqbalnetwork.models.request;

import com.iqbalnetwork.models.CourseInfo;
import com.iqbalnetwork.models.CourseType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CourseDto {

    @NotBlank(message = "{invalid.title.required}")
    private String title;
    @Length(min = 100, max = 1000,message = "description length is between 100-1000 character long")
    private String description;
    @NotBlank(message = "Link is required")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String link;
    private CourseType courseType;
    private CourseInfo courseInfo;
}
