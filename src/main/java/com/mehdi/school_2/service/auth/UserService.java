package com.mehdi.school_2.service.auth;

import com.mehdi.school_2.config.JwtProvider;
import com.mehdi.school_2.dto.UserResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.entity.user.UserRoleName;
import com.mehdi.school_2.exception.CustomException;
import com.mehdi.school_2.repository.user.UserRepository;
import com.mehdi.school_2.token.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;



    public UserService( AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    public User save(UserResponseDTO userResponseDTO) {
        UserRoleName role = UserRoleName.ADMIN;
        if (!userRepository.existsByUsername(userResponseDTO.getUsername())) {
            User newUser = new User();
            newUser.setUsername(userResponseDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(userResponseDTO.getPassword()));
            newUser.setFullName(userResponseDTO.getFullName());
            newUser.setRole(role);
            User savedUser =  userRepository.save(newUser);

            return savedUser ;
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User update(Integer id, UserResponseDTO userResponseDTO) {
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

    public String delete(Integer userId) {
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

    public User findById(Integer id) {
        return userRepository
                .findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }



//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepository.findByUsername(request.getEmail());
//        var jwtToken = jwtService.generateToken(user);
//
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//
//                .build();
//    }


//    private void saveUserToken(User user, String jwtToken) {
//        var token = TokenEntity.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }
//
//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }


    public User findUserByJwt(String jwt) {
        String username = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByUsername(username);
        return user;
    }


}
