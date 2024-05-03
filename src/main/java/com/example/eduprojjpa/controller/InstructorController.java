package com.example.eduprojjpa.controller;

import com.example.eduprojjpa.entity.CourseEntity;
import com.example.eduprojjpa.entity.LecMatEntity;
import com.example.eduprojjpa.repository.CourseRepository;
import com.example.eduprojjpa.repository.LecMatRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class InstructorController {

    private final CourseRepository courseRepository;
    private final LecMatRepository lecMatRepository;

    // 강사가 강의를 개설하는 메서드입니다.
    @PostMapping("/instructor/course/open")
    @Transactional
    public ResponseEntity<String> CourseOpen(@RequestBody CourseEntity courseEntity) {

            courseRepository.save(courseEntity);
            return ResponseEntity.ok().body("course open successful");
    }

    // 강사가 강의 자료를 업로드하는 메서드입니다.
    @PostMapping("/instructor/course/data-upload")
    @Transactional
    public ResponseEntity<String> DataUpload(@RequestBody LecMatEntity lecMatEntity) {

        lecMatRepository.save(lecMatEntity);

        CourseEntity courseEntity = courseRepository.findByCourseId(lecMatEntity.getCourseId());

        List<LecMatEntity> lecMatEntities = new ArrayList<>();
        lecMatEntities.add(lecMatEntity);
        courseEntity.setLectureMaterials(lecMatEntities);

        courseRepository.save(courseEntity);
        return ResponseEntity.ok().body("data upload successful");
    }

    // 강사가 과제를 업로드하는 메서드입니다.



}
