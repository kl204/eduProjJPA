package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "edu_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    // 유저 타입 (강사 or 학생)
    @Column(name = "user_type")
    private String userType;

    // 강사는 여러 개의 강의를 개설 할 수 있다.
    @OneToMany(mappedBy = "openedUser" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseEntity> coursesOpen;

    // 학생은 여러 개의 수업을 등록 할 수 있다.
    @OneToMany(mappedBy = "enrolledUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnrollEntity> courseEnroll;

    // 학생은 여러 개의 과제 제출을 할 수 있다.
    @OneToMany(mappedBy = "userEntity")
    private List<SubmissionEntity> submissions;



}
