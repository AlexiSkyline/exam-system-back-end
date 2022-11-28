package com.exams.system.app.service;

import com.exams.system.app.models.domain.User;

public interface IUserService {
    public User save( User user );
    public User getUserByUsername( String username );
    public User delete( Long userId );
}