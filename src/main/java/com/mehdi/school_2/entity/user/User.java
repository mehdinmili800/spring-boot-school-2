package com.mehdi.school_2.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mehdi.school_2.entity.user.group.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String username;

    @JsonIgnore

    private String password;


    private String fullName;




    public User(String username, String password, String fullName ) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }



    @Enumerated(EnumType.STRING)
    private UserRoleName role;



    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Student> students = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<TokenEntity> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return Math.toIntExact(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
