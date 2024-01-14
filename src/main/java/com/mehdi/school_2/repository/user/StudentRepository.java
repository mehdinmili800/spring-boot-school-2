package com.mehdi.school_2.repository.user;

import com.mehdi.school_2.entity.user.group.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    void deleteById(Integer id);
}
