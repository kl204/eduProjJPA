package com.example.eduprojjpa.controller;

import com.example.eduprojjpa.common.annotation.S3DeleteObject;
import com.example.eduprojjpa.entity.CourseEntity;
import com.example.eduprojjpa.entity.LecMatEntity;
import com.example.eduprojjpa.entity.UserEntity;
import com.example.eduprojjpa.repository.CourseRepository;
import com.example.eduprojjpa.repository.LecMatRepository;
import com.example.eduprojjpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class InstructorController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LecMatRepository lecMatRepository;
    private final HttpSession httpSession;

    // 강사 자신의 정보를 반환하는 메서드입니다.
    @GetMapping("/instructor/info")
    @Transactional
    public ResponseEntity<UserEntity> InstructorInfo() {

        String userName = (String) httpSession.getAttribute("userName");

        UserEntity userEntity = userRepository.findByUserName(userName);

        return ResponseEntity.ok().body(userEntity);
    }

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

    // 강사가 강의 자료를 삭제하는 메서드입니다.
    @S3DeleteObject
    @DeleteMapping("/instructor/course/data-delete")
    @Transactional
    public ResponseEntity<String> DeleteData(@RequestBody LecMatEntity lecMatEntity) {

        lecMatRepository.delete(lecMatEntity);
        return ResponseEntity.ok().body("Deleted successful");
    }



}
