package com.exams.system.app.service.impl;

import com.exams.system.app.models.User;
import com.exams.system.app.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional<User> userFound = this.userRepository.findByUsername( username );
        if( userFound.isEmpty() ) {
            throw  new UsernameNotFoundException( "User not exits" );
        }

        return userFound.get();
    }
}