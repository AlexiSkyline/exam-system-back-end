package com.exams.system.app.controller;

import com.exams.system.app.security.jwt.JwtUtils;
import com.exams.system.app.models.request.LoginRequest;
import com.exams.system.app.models.response.JwtResponse;
import com.exams.system.app.models.domain.User;
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

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtils jwtUtils;

    @PostMapping( "/login" )
    public ResponseEntity<?> login( @RequestBody @Valid LoginRequest jwtRequest ) throws Exception {
        try{
            this.authenticate( jwtRequest.getUsername(), jwtRequest.getPassword() );
        } catch ( UsernameNotFoundException exception ) {
            exception.printStackTrace();
            throw new Exception( "User not Found" );
        }

        UserDetails userDetails =  this.userDetailService.loadUserByUsername( jwtRequest.getUsername() );
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok( new JwtResponse( token ));
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