package com.exams.system.app.service.impl;

import com.exams.system.app.models.User;
import com.exams.system.app.models.UserDetailsImpl;
import com.exams.system.app.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User userFound = this.userRepository.findByUsername( username )
                .orElseThrow( () -> new UsernameNotFoundException( "User Not Found with username: " + username ) );

        return UserDetailsImpl.build( userFound );
    }
}