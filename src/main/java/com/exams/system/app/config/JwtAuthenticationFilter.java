package com.exams.system.app.config;

import com.exams.system.app.service.impl.UserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
        String username = null;
        String jwtToken = this.parserJwt( request );

        try{
            username = this.jwtUtils.extractUsername( jwtToken );
        } catch ( ExpiredJwtException exception ){
            System.out.println( "Token has expired" );
        } catch ( Exception e ){
            e.printStackTrace();
        }

        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = this.userDetailService.loadUserByUsername( username );
            if( this.jwtUtils.validateToken( jwtToken, userDetails ) ){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );
                usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ));

                SecurityContextHolder.getContext().setAuthentication( usernamePasswordAuthenticationToken );
            } else{
                System.out.println( "The token is not valid" );
            }
        }
        filterChain.doFilter( request,response );
    }

    private String parserJwt( HttpServletRequest request ) {
        String headerAuth = request.getHeader( "Authorization" );
        if ( StringUtils.hasText( headerAuth ) && headerAuth.startsWith( "Bearer " ) ) {
            return headerAuth.substring( 7, headerAuth.length() );
        } else{
            System.out.println( "Invalid token, does not start with bearer string" );
        }

        return null;
    }
}
