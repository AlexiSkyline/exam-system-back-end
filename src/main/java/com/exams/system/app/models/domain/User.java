package com.exams.system.app.models.domain;

import com.exams.system.app.models.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "users" )
@Getter @Setter
public class User {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    @NotBlank
    @Size( min = 4, max = 20 )
    @Column( unique = true )
    private String username;
    @NotBlank
    @Size( max = 20 )
    @Email
    @Column( unique = true )
    private String email;
    @NotBlank
    @Size( max = 60 )
    @JsonIgnore
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Size( max = 15 )
    private String phoneNumber;
    private boolean enabled = true;
    private String profile;
    @ManyToMany( fetch = EAGER )
    @JoinTable( name = "user_roles", joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" ))
    private Set<Role> roles = new HashSet<>();
}