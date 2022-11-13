package com.exams.system.app.service;

import com.exams.system.app.models.Role;
import com.exams.system.app.models.TypeRole;

import java.util.Optional;

public interface IRoleService {
    Role save( TypeRole role );
    public Optional<Role> findByName( TypeRole name );
}