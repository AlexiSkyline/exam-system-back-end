package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/users" )
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping( "/{username}" )
    public ResponseEntity<?> getUser( @PathVariable String username ) {
        return ResponseHandler.responseBuild( OK, "Requested User By Username are given here", this.userService.getUserByUsername( username ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deleteUser( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "User Delete Successfully", this.userService.delete( id ) );
    }
}