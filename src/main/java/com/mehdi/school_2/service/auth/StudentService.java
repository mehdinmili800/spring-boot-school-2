package com.mehdi.school_2.service.auth;

import com.mehdi.school_2.dto.StudentResponseDTO;
import com.mehdi.school_2.entity.user.User;
import com.mehdi.school_2.entity.user.group.Gender;
import com.mehdi.school_2.entity.user.group.Student;
import com.mehdi.school_2.repository.user.StudentRepository;
import com.mehdi.school_2.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public Student create(StudentResponseDTO studentResponseDTO,Integer userId) {
        User user = userRepository.findUserById(userId);
        Student student = new Student();

        student.setAddress(studentResponseDTO.getAddress());

        student.setStudent(user);
        studentRepository.save(student);
        return student;
    }
}
