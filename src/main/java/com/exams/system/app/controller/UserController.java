package com.exams.system.app.controller;

import com.exams.system.app.models.Role;
import com.exams.system.app.models.User;
import com.exams.system.app.service.IRoleService;
import com.exams.system.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

import static com.exams.system.app.models.TypeRole.ROLE_NORMAL;

@RestController
@RequestMapping( "/users" )
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;

    @PostMapping( "/" )
    public User saveUser( @RequestBody User user ) throws Exception {
        Role normal_role = this.roleService.findByName( ROLE_NORMAL ).orElse( this.roleService.save( ROLE_NORMAL ) );
        user.setRoles( Collections.singleton( normal_role ) );

        return this.userService.save( user );
    }

    @GetMapping( "/{username}" )
    public User getUser( @PathVariable String username ) {
        return this.userService.getUserByUsername( username );
    }

    @DeleteMapping( "/{id}" )
    public User deleteUser( @PathVariable String id ) {
        return this.userService.getUserByUsername( id );
    }
}