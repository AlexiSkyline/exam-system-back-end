package com.exams.system.app.service;

import com.exams.system.app.models.domain.Role;
import com.exams.system.app.models.TypeRole;

import java.util.Optional;

public interface IRoleService {
    public Role save( TypeRole role );
    public Optional<Role> findByName( TypeRole name );
}