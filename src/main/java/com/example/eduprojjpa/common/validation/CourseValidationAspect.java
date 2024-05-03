package com.example.eduprojjpa.common.validation;

import com.example.eduprojjpa.common.exception.CourseAlreadyExistsException;
import com.example.eduprojjpa.entity.CourseEntity;
import com.example.eduprojjpa.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

// 이미 개설된 강의의 유무에 대한 Validation입니다.
@Aspect
@Component
@AllArgsConstructor
public class CourseValidationAspect {

    private final CourseRepository courseRepository;

    @Before("execution(* com.example.eduprojjpa.controller.InstructorController.courseOpen(..)) && args(courseEntity)")
    public void validateCourseExistence(CourseEntity courseEntity) {
        Optional<CourseEntity> courseCheck = Optional.ofNullable(courseRepository.findByCourseName(courseEntity.getCourseName()));

        if (courseCheck.isPresent()) {
            throw new CourseAlreadyExistsException("Course already exists");
        }
    }
}

