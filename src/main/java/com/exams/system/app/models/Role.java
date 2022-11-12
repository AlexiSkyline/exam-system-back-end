package com.exams.system.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "roles" )
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    @Enumerated( EnumType.STRING )
    @Column( length = 20 )
    private TypeRole name;
}