package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.User;
import com.exams.system.app.repository.IUserRepository;
import com.exams.system.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save( User user ) {
        Optional<User> localUser = this.userRepository.findByUsername( user.getUsername() );
        if( localUser.isPresent() ) {
            System.out.println( "Username already exist" );
        }
        user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
        return this.userRepository.save( user );
    }

    @Override
    @Transactional( readOnly = true )
    public User getUserByUsername( String username ) {
        return this.userRepository.findByUsername( username ).orElse( null );
    }

    @Override
    @Transactional
    public User delete( Long userId ) {
        User userFound = this.userRepository.findById( userId ).get();
        this.userRepository.deleteById( userId );

        return userFound;
    }
}