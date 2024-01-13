package com.mehdi.school_2.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mehdi.school_2.entity.user.Permission.*;

@RequiredArgsConstructor
public enum UserRoleName {
//    ROLE_STUDENT,
//    ROLE_TEACHER,
//    ROLE_HEADTEACHER,

ADMIN(
        Set.of(
                ADMIN_READ,
                ADMIN_UPDATE,
                ADMIN_DELETE,
                ADMIN_CREATE

        )
);


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
