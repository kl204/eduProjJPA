package com.example.eduprojjpa.controller;

import com.example.eduprojjpa.entity.UserEntity;
import com.example.eduprojjpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class StudentController {


    private final UserRepository userRepository;

    private final HttpSession httpSession;

    // 학생으로 로그인 하는 메서드 입니다.
    @GetMapping("/student/login")
    public ResponseEntity<Optional<UserEntity>> StudentLogin(@Param("userName") String userName){

        // 스프링 세션에 회원 정보를 넣기 전에 회원 정보가 이미 있는지 확인합니다.
       UserEntity existingUser = userRepository.findByUsername(userName);

        if (existingUser==null) {
            // 회원 정보가 없으면 새로운 회원을 생성하여 저장합니다.
            UserEntity newUser = new UserEntity();
            newUser.setUserName("Student");
            userRepository.save(newUser);

            // 스프링 세션에 회원 정보를 저장합니다.
            httpSession.setAttribute("userType","Student");

            return ResponseEntity.ok().body(Optional.of(newUser));

        }else{
            //회원이 이미 있으면 그대로 반환합니다.
            return ResponseEntity.ok().body(Optional.of(existingUser));
        }

    }
}
