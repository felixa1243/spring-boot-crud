package com.iqbalnetwork.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Accessors(chain = true)
@Entity
@Table(name = "m_course")
public class Course {
    @Id
    @Column(name = "course_id", nullable = false)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    String courseId;
    @Column(name = "description", columnDefinition = "TEXT")
    String description;
    @Column(name = "title", length = 15)
    String title;
    @Column(name = "link", length = 100)
    String link;
}
