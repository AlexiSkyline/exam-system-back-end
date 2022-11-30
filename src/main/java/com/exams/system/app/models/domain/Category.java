package com.exams.system.app.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "categories" )
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @OneToMany( mappedBy = "category", cascade = ALL )
    @JsonIgnore
    private Set<Questionnaire> Questionnaire = new LinkedHashSet<>();
}