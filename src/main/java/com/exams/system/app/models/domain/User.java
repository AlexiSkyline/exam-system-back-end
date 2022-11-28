package com.exams.system.app.models.domain;

import com.exams.system.app.models.domain.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean enabled = true;
    private String profile;
    @ManyToMany( fetch = EAGER )
    @JoinTable( name = "user_roles", joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" ))
    private Set<Role> roles = new HashSet<>();
}