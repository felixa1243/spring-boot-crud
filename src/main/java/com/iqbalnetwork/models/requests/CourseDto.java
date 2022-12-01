package com.iqbalnetwork.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class CourseDto {
    @NotBlank(message = "{invalid.title.required}")
    private String title;
    @Length(min = 100, max = 1000)
    private String description;
    @NotBlank(message = "Link is required")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String link;
}
