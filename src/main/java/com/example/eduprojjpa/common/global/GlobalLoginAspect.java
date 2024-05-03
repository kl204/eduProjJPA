package com.example.eduprojjpa.common.global;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


//테스트를 위한 로그인 AOP입니다.
@Aspect
@Component
@RequiredArgsConstructor
public class GlobalLoginAspect {

    private final HttpSession httpSession;

    // 강사일 경우 로그인 AOP
    @Before("execution(* com.example.eduprojjpa.controller.InstructorController.*(..))")
    public void loginInstructorInit() {
        httpSession.setAttribute("userName","김선규");
        httpSession.setAttribute("userType","Instructor");
    }

    // 학생일 경우 로그인 AOP
    @Before("execution(* com.example.eduprojjpa.controller.StudentController.*(..))")
    public void loginStudentInit() {
        httpSession.setAttribute("userName","김선규");
        httpSession.setAttribute("userType","Student");
    }
}
