package com.exams.system.app.controller;

import com.exams.system.app.config.JwtUtils;
import com.exams.system.app.models.LoginRequest;
import com.exams.system.app.models.JwtResponse;
import com.exams.system.app.models.User;
import com.exams.system.app.service.UserDetailService;
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

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtils jwtUtils;

    @PostMapping( "/login" )
    public ResponseEntity<?> login( @RequestBody LoginRequest jwtRequest ) throws Exception {
        try{
            this.authenticate( jwtRequest.getUsername(), jwtRequest.getPassword() );
        }catch ( UsernameNotFoundException exception ) {
            exception.printStackTrace();
            throw new Exception( "User not Found" );
        }

        UserDetails userDetails =  this.userDetailService.loadUserByUsername( jwtRequest.getUsername() );
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok( new JwtResponse( token ));
    }

    private void authenticate( String username,String password ) throws Exception {
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
        }catch ( DisabledException exception ) {
            throw  new Exception( "USER DISABLED " + exception.getMessage() );
        }catch ( BadCredentialsException e ) {
            throw new Exception( "Invalid credentials " + e.getMessage() );
        }
    }
}