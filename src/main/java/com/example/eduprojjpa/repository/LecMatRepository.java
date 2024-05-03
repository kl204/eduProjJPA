package com.example.eduprojjpa.repository;

import com.example.eduprojjpa.entity.LecMatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecMatRepository extends JpaRepository<LecMatEntity, Long> {

    LecMatEntity findByCourseEntity_CourseIdAndLecName(long courseId, String lecMatName);
}
