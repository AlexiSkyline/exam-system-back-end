package com.exams.system.app.config;

import com.exams.system.app.security.jwt.JwtAuthenticationEntryPoint;
import com.exams.system.app.security.jwt.JwtAuthenticationFilter;
import com.exams.system.app.service.impl.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity( prePostEnabled = true )
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final UserDetailService userDetailService;

    @Bean
    public JwtAuthenticationFilter authTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService( this.userDetailService );
        authProvider.setPasswordEncoder( this.passwordEncoder() );

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity httpSecurity ) throws Exception
    {
        httpSecurity.cors()
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint( this.unauthorizedHandler )
                .and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .authorizeRequests().antMatchers( "/login", "/users/", "/category/", "/questionnaire/", "/question/" ).permitAll()
                .anyRequest().authenticated();

        httpSecurity.authenticationProvider( this.authenticationProvider() );
        httpSecurity.addFilterBefore( this.authTokenFilter(), UsernamePasswordAuthenticationFilter.class );

        return httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry ) {
                registry.addMapping( "/**" )
                        .allowedOrigins( "http://localhost:4200" ).allowedMethods( "GET", "POST", "DELETE", "PUT" )
                        .maxAge( 3600 );
            }
        };
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename( "classpath:messages" );
        messageSource.setDefaultEncoding( "UTF-8" );
        return messageSource;
    }
}