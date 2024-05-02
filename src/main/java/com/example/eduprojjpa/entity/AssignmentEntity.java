package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "edu_assignment")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private long assignId;

    @JoinColumn(name = "course_id")
    private long courseId;

    @Column(name = "assign_name")
    private String assignName;

    @Column(name = "assign_desc")
    private  String assignDesc;

    @Column(name = "asisgn_data")
    private String assignData;

    @Column(name = "assign_deadline")
    private Date assginDeadline;

    @ManyToOne
    private CourseEntity courseEntity;

    @ManyToOne
    private UserEntity userEntity;
}
