package com.iqbalnetwork.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "m_course")
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "link", nullable = false, length = 50)
    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id")
    private CourseInfo courseInfo;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "file_name")
    private String fileName;
}
