package com.example.eduprojjpa.controller;

import com.example.eduprojjpa.common.utils.SeoulDateTimeProvider;
import com.example.eduprojjpa.entity.CourseEntity;
import com.example.eduprojjpa.entity.EnrollEntity;
import com.example.eduprojjpa.entity.LecMatEntity;
import com.example.eduprojjpa.entity.UserEntity;
import com.example.eduprojjpa.repository.CourseRepository;
import com.example.eduprojjpa.repository.EnrollRepository;
import com.example.eduprojjpa.repository.LecMatRepository;
import com.example.eduprojjpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
@AllArgsConstructor
public class StudentController {


    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final HttpSession httpSession;
    private final SeoulDateTimeProvider seoulDateTimeProvider;
    private final EnrollRepository enrollRepository;
    private final LecMatRepository lecMatRepository;

    // 학생의 수강 신청 메서드
    @PostMapping("/student/course/enroll")
    @Transactional
    public ResponseEntity<String> courseOpen(@Param("courseName")String courseName) {

        String userName = (String) httpSession.getAttribute("userName");

        UserEntity userEntity = userRepository.findByUsername(userName);
        CourseEntity courseEntity = courseRepository.findByCourseName(courseName);

        EnrollEntity enrollEntity = new EnrollEntity();
        enrollEntity.setUserId(userEntity.getUserId());
        enrollEntity.setEnrolledUser(userEntity);
        enrollEntity.setCourseId(courseEntity.getCourseId());
        enrollEntity.setCourseEntity(courseEntity);
        enrollEntity.setEnrollDate(seoulDateTimeProvider.getSeoulDateTime());
        enrollRepository.save(enrollEntity);

        List<EnrollEntity> enrollEntityList = new ArrayList<>();
        enrollEntityList.add(enrollEntity);
        courseEntity.setEnrolledCourse(enrollEntityList);
        courseRepository.save(courseEntity);

        return ResponseEntity.ok().body("Course Enrollment success");
    }

    // 학생 자신의 강의 리스트 반환 메서드
    @GetMapping("/student/course/List")
    public ResponseEntity<List<EnrollEntity>> GetCourseList(){

        String userName = (String) httpSession.getAttribute("userName");

        UserEntity userEntity = userRepository.findByUsername(userName);

        List<EnrollEntity> enrollEntityList = userEntity.getCourseEnroll();
        return ResponseEntity.ok().body(enrollEntityList);
    }

    // 자료 다운로드 메서드
    @PostMapping("/student/lec-mat-download")
    public ResponseEntity<String> LecMatDownload(@RequestBody long courseId, String lecName){

        LecMatEntity lecMatEntity = lecMatRepository.findByCourseEntity_CourseIdAndLecName(courseId,lecName);

        return ResponseEntity.ok().body(lecMatEntity.getLecData());
    }

}
