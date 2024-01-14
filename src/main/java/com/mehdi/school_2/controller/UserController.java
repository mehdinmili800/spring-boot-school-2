package com.mehdi.school_2.controller;

import com.mehdi.school_2.config.JwtProvider;
import com.mehdi.school_2.dto.UserResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.repository.user.UserRepository;
import com.mehdi.school_2.service.auth.CustomUserDetailsService;
import com.mehdi.school_2.service.auth.UserService;
import com.mehdi.school_2.token.AuthResponse;
import com.mehdi.school_2.token.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customerUserDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping(value = "/user/create")
    public AuthResponse addUser(@RequestBody UserResponseDTO userResponseDTO){
        User savedUser = userService.save(userResponseDTO);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUsername(),savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Register Success");
        return res;
    }

    @GetMapping(value = "/user/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getUsername(),loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password){
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
        if (userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
