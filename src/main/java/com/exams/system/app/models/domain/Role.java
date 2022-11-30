package com.exams.system.app.models.domain;

import com.exams.system.app.models.TypeRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "roles" )
@NoArgsConstructor
@Setter @Getter
public class Role {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    @Enumerated( EnumType.STRING )
    @Column( length = 20 )
    private TypeRole name;

    public Role( TypeRole name ) {
        this.name = name;
    }
}