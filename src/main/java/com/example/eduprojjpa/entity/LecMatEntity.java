package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "edu_lec_mat")
public class LecMatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lec_met_id")
    private long lecMetID;

    @JoinColumn(name = "course_id")
    private long courseId;

    @Column(name = "lec_name")
    private String lecName;

    @Column(name = "lec_desc")
    private String lecDese;

    @Column(name = "lec_data")
    private String lecData;

    @Column(name = "lec_upl_date")
    private Date lecUplDate;

    @ManyToOne
    private CourseEntity courseEntity;
}
