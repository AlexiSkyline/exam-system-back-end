package com.exams.system.app.service;

import com.exams.system.app.models.Role;
import com.exams.system.app.models.TypeRole;
import com.exams.system.app.models.User;
import com.exams.system.app.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Role save( TypeRole role ) throws Exception {
        Optional<Role> roleFound = this.roleRepository.findByName( role );
        if( roleFound.isPresent() ) {
            System.out.println( "Role already exist" );
            throw new Exception( "The Role is already present" );
        }

        return this.roleRepository.save( new Role( role ) );
    }

    @Override
    public Optional<Role> findByName( TypeRole name ) {
        return this.roleRepository.findByName( name );
    }
}