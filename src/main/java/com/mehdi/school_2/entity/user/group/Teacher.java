package com.mehdi.school_2.entity.user.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mehdi.school_2.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
public class Teacher {

    /**
     * Id field [GENERATED AUTOMATICALLY].
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User object to teacher. This property connects the teacher to
     * the account.
     */
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private User teacher;

    /**
     * Teacher e-mail.
     */
    @Column(name = "email", length = 64)
    private String email;

    /**
     * Teacher phone number.
     */
    @Column(name = "phone", nullable = false, length = 24)
    private String phone;

    /**
     *  Constructor to make a new instance.
     *
     * @param teacher User object to teacher.
     * @param email Teacher e-mail.
     * @param phone Teacher phone number.
     */
    public Teacher(User teacher, String email, String phone) {
        this.teacher = teacher;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Courses that the teacher teaching.
     */
//    @JsonIgnore
//    @OneToMany(mappedBy = "teacher")
//    private List<Course> courses = new ArrayList<>();
}
