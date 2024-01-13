package com.mehdi.school_2.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
//    HEADTEACHER_READ("head-teacher:read"),
//    HEADTEACHER_UPDATE("head-teacher:update"),
//    HEADTEACHER_CREATE("head-teacher:create"),
//    HEADTEACHER_DELETE("head-teacher:delete")





    ;

    @Getter
    private final String permission;
}
