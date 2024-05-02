package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "edu_enroll")
public class EnrollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private long enrollmentId;

    @JoinColumn(name = "user_id")
    private long userId;

    @JoinColumn(name = "course_id")
    private long courseId;

    @Column(name = "enroll_date")
    private Date enrollDate;

    //수강 객체
    @ManyToOne
    private CourseEntity courseEntity;

    // 수강 신청 유저 객체
    @ManyToOne
    private UserEntity enrolledUser;

}
