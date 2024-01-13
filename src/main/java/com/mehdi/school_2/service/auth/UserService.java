package com.mehdi.school_2.service.auth;

import com.mehdi.school_2.config.JwtService;
import com.mehdi.school_2.dto.UserResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.entity.user.UserRoleName;
import com.mehdi.school_2.exception.CustomException;
import com.mehdi.school_2.repository.user.UserRepository;
import com.mehdi.school_2.token.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private final JwtService jwtService;

    public UserService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public AuthenticationResponse save(UserResponseDTO userResponseDTO) {
        UserRoleName role = UserRoleName.ADMIN;
        if (!userRepository.existsByUsername(userResponseDTO.getUsername())) {
            User newUser = new User();
            newUser.setUsername(userResponseDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(userResponseDTO.getPassword()));
            newUser.setFullName(userResponseDTO.getFullName());
            newUser.setRole(role);
            userRepository.save(newUser);
            var jwtToken = jwtService.generateToken(newUser);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build()
                    ;
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User update(Long id, UserResponseDTO userResponseDTO) {
        User user = userRepository.getOne(id);

        if (!userRepository.existsByUsername(userResponseDTO.getUsername())) {
            user.setUsername(userResponseDTO.getUsername());
            if (userResponseDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userResponseDTO.getPassword()));
            }
            user.setFullName(userResponseDTO.getFullName());
            userRepository.save(user);
            return user;
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String delete(Long userId) {
        User user = userRepository.getOne(userId);
        userRepository.delete(user);
        return userId.toString();
    }

    public boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }

    public void resetCredentials(String username) {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository
                .findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
