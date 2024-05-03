package com.example.eduprojjpa.common.global;

import com.example.eduprojjpa.entity.UserEntity;
import com.example.eduprojjpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;


//테스트를 위한 로그인 AOP입니다.
@Aspect
@Component
@RequiredArgsConstructor
public class GlobalLoginAspect {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    // 강사일 경우 로그인 AOP
    @Before("execution(* com.example.eduprojjpa.controller.InstructorController.*(..))")
    public void loginInstructorInit() {

        Optional<UserEntity> userEntityOptional = Optional.ofNullable(userRepository.findByUserName("김선규"));

        if(userEntityOptional.isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("김선규");
            userEntity.setUserEmail("kl204@naver.com");
            userEntity.setUserType("Instructor");
            userRepository.save(userEntity);
        }

        httpSession.setAttribute("userName","김선규");
        httpSession.setAttribute("userType","Instructor");
    }

    // 학생일 경우 로그인 AOP
    @Before("execution(* com.example.eduprojjpa.controller.StudentController.*(..))")
    public void loginStudentInit() {

        Optional<UserEntity> userEntityOptional = Optional.ofNullable(userRepository.findByUserName("이강인"));

        if(userEntityOptional.isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("이강인");
            userEntity.setUserEmail("lee@naver.com");
            userEntity.setUserType("Student");
            userRepository.save(userEntity);
        }

        httpSession.setAttribute("userName","이강인");
        httpSession.setAttribute("userType","Student");
    }
}
