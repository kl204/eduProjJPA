package com.example.eduprojjpa.repository;

import com.example.eduprojjpa.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    CourseEntity findByCourseName(String courseName);

    CourseEntity findByCourseId(long courseId);
}
