package com.example.eduprojjpa.service;

import com.example.eduprojjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorService {

    private final UserRepository userRepository;



}
