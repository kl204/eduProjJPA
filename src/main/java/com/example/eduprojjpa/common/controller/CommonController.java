package com.example.eduprojjpa.common.controller;

import com.example.eduprojjpa.entity.CourseEntity;
import com.example.eduprojjpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final CourseRepository courseRepository;

    // 모든 강의 리스트 반환 메서드입니다.
    @GetMapping("/course/List")
    @Transactional
    public ResponseEntity<List<CourseEntity>> CourseList(){

        List<CourseEntity> courseEntity = courseRepository.findAll();

        return ResponseEntity.ok().body(courseEntity);
    }
}
