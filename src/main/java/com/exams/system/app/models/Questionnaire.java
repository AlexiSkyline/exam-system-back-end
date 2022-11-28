package com.exams.system.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "questionnaires" )
@Getter @Setter
public class Questionnaire {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Long id;
    private String title;
    private String description;
    private Integer maxPoints;
    private Integer numberQuestions;
    private boolean status = false;
    @ManyToOne( fetch = EAGER )
    private Category category;
    @OneToMany( mappedBy = "questionnaire", fetch = LAZY, cascade = ALL )
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();
}