package com.mehdi.school_2.controller;

import com.mehdi.school_2.dto.StudentResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.entity.user.group.Student;
import com.mehdi.school_2.service.auth.StudentService;
import com.mehdi.school_2.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    UserService userService;

    @PostMapping("/student/create")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String jwt,
                                    @RequestBody StudentResponseDTO studentResponseDTO) {

        User reqUser = userService.findUserByJwt(jwt);

        if (reqUser != null) {
            Student student = studentService.create(studentResponseDTO, reqUser.getId());
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


}
