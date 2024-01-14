package com.mehdi.school_2.repository.user;

import com.mehdi.school_2.entity.user.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    public  User findUserById(Integer userId) ;


}
