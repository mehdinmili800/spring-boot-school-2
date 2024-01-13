package com.mehdi.school_2.controller;

import com.mehdi.school_2.dto.UserResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.service.auth.UserService;
import com.mehdi.school_2.token.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/user/create")
    public ResponseEntity<?> addUser(@RequestBody UserResponseDTO userResponseDTO
                                    ) {
        return ResponseEntity.ok(userService.save(userResponseDTO));
    }
}
