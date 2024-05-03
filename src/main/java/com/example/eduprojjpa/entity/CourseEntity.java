package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "edu_course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_name")
        private String courseName;

    @Column(name = "course_desc")
    private String courseDesc;

    @Column(name = "course_start")
    private Date courseStart;

    @Column(name = "course_end")
    private Date courseEnd;

    @JoinColumn(name = "user_id")
    private long userId;

    // 강의는 여러 개의 등록을 가질 수 있다.
    @OneToMany(mappedBy = "courseEntity")
    private List<EnrollEntity> enrolledCourse;

    // 강의는 여러 개의 강의 자료를 가질 수 있다.
    @OneToMany(mappedBy = "courseEntity")
    private List<LecMatEntity> lectureMaterials;

    // 강의는 여러 개의 과제를 가질 수 있다.
    @OneToMany(mappedBy = "courseEntity")
    private List<AssignmentEntity> assignmentEntities;

    // 강의 객체
    @ManyToOne
    private UserEntity openedUser;

}
