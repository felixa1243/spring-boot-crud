package com.iqbalnetwork.models;

import com.iqbalnetwork.utils.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "course_info")
@Accessors(chain = true)
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_info_id")
    private String courseInfoId;
    @Column
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private Level level;
}
