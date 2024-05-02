package com.example.eduprojjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "edu_submission")
public class SubmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subm_id")
    private long submId;

    @JoinColumn(name = "assign_id")
    private long assignId;

    @JoinColumn(name = "user_id")
    private long userId;

    @Column(name = "subm_data")
    private String submData;

    @Column(name = "subm_score")
    private int submScore;

    @ManyToOne
    private UserEntity userEntity;
}
