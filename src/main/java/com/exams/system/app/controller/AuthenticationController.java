package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.models.domain.Role;
import com.exams.system.app.security.jwt.JwtUtils;
import com.exams.system.app.models.request.LoginRequest;
import com.exams.system.app.models.response.LoginResponse;
import com.exams.system.app.models.domain.User;
import com.exams.system.app.service.IRoleService;
import com.exams.system.app.service.IUserService;
import com.exams.system.app.service.impl.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;

import static com.exams.system.app.models.TypeRole.ROLE_USER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final IRoleService roleService;
    private final IUserService userService;
    private final UserDetailService userDetailService;
    private final JwtUtils jwtUtils;

    @PostMapping( "/login" )
    public ResponseEntity<?> login( @RequestBody @Valid LoginRequest jwtRequest ) throws Exception {
        User userFound = null;
        try{
            this.authenticate( jwtRequest.getUsername(), jwtRequest.getPassword() );
            userFound = this.userService.getUserByUsername( jwtRequest.getUsername() );
        } catch ( UsernameNotFoundException exception ) {
            exception.printStackTrace();
            throw new Exception( "User not Found" );
        }

        UserDetails userDetails =  this.userDetailService.loadUserByUsername( jwtRequest.getUsername() );
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseHandler.responseBuild( OK, "Login Successfully", new LoginResponse( userFound, token ));
    }

    @PostMapping( "/signup" )
    public ResponseEntity<?> signUp( @RequestBody @Valid User user ) {
        user.setProfile( "default.png" );
        Role normal_role = this.roleService.findByName(ROLE_USER).orElseGet(() -> this.roleService.save(ROLE_USER) );
        user.setRoles( Collections.singleton( normal_role ) );
        User newUser = this.userService.save( user );

        UserDetails userDetails =  this.userDetailService.loadUserByUsername( user.getUsername() );
        String token = this.jwtUtils.generateToken( userDetails );
        return ResponseHandler.responseBuild( CREATED, "User Created Successfully", new LoginResponse( newUser, token ));
    }

    private void authenticate( String username, String password ) throws Exception {
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
        } catch ( DisabledException exception ) {
            throw  new Exception( "USER DISABLED " + exception.getMessage() );
        } catch ( BadCredentialsException e ) {
            throw new Exception( "Invalid credentials " + e.getMessage() );
        }
    }

    @GetMapping( "/current-user" )
    public User getUserLoggedIn( Principal principal ) {
        return (User) this.userDetailService.loadUserByUsername( principal.getName() );
    }
}