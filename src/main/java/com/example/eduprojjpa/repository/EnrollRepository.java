package com.example.eduprojjpa.repository;

import com.example.eduprojjpa.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<EnrollEntity,Long>
{

    EnrollEntity findByCourseName(String courseName);
}
