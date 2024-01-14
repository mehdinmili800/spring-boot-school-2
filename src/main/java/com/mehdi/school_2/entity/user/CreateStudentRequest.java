package com.mehdi.school_2.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {

    private String username;
    private String password;
    private String fullName;
    private String address;
}
