package com.exams.system.app.models;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
    private final TypeRole authority;

    @Override
    public String getAuthority() {
        return this.authority.name();
    }
}