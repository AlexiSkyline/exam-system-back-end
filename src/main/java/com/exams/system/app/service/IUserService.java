package com.exams.system.app.service;

import com.exams.system.app.models.Role;
import com.exams.system.app.models.User;

import java.util.Set;

public interface IUserService {
    public User save( User user, Set<Role> roles ) throws Exception;
    public User getUserByUsername( String username );
    public void delete( Long userId );
}