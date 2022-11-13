package com.exams.system.app.service;

import com.exams.system.app.models.User;
import com.exams.system.app.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    @Transactional
    public User save( User user ) {
        Optional<User> localUser = this.userRepository.findByUsername( user.getUsername() );
        if( localUser.isPresent() ) {
            System.out.println( "Username already exist" );
        }

        return this.userRepository.save( user );
    }

    @Override
    @Transactional( readOnly = true )
    public User getUserByUsername( String username ) {
        return this.userRepository.findByUsername( username ).orElse( null );
    }

    @Override
    @Transactional
    public void delete( Long userId ) {
        this.userRepository.deleteById( userId );
    }
}