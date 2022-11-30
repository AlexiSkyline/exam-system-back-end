package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.Role;
import com.exams.system.app.models.TypeRole;
import com.exams.system.app.models.exception.FieldAlreadyUsedException;
import com.exams.system.app.models.exception.RecordNotFoundException;
import com.exams.system.app.repository.IRoleRepository;
import com.exams.system.app.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Role save( TypeRole role ) {
        Optional<Role> roleFound = this.roleRepository.findByName( role );
        if( roleFound.isPresent() ) {
            throw new FieldAlreadyUsedException( "Name", "Role" );
        }

        return this.roleRepository.save( new Role( role ) );
    }

    @Override
    public Optional<Role> findByName( TypeRole name ) {
        return this.roleRepository.findByName( name );
    }
}