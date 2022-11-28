package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.models.domain.Role;
import com.exams.system.app.models.domain.User;
import com.exams.system.app.service.IRoleService;
import com.exams.system.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.exams.system.app.models.TypeRole.ROLE_USER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/users" )
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;

    @PostMapping( "/" )
    public ResponseEntity<?> saveUser( @RequestBody User user ) {
        user.setProfile( "default.png" );
        Role normal_role = this.roleService.findByName(ROLE_USER).orElseGet(() -> this.roleService.save(ROLE_USER) );
        user.setRoles( Collections.singleton( normal_role ) );

        return ResponseHandler.responseBuild( CREATED, "User Created Successfully", this.userService.save( user )  );
    }

    @GetMapping( "/{username}" )
    public ResponseEntity<?> getUser( @PathVariable String username ) {
        return ResponseHandler.responseBuild( OK, "Requested User By Username are given here", this.userService.getUserByUsername( username ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deleteUser( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "User Delete Successfully", this.userService.delete( id ) );
    }
}