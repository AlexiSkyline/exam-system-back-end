package com.exams.system.app.models.domain;

import com.exams.system.app.models.domain.Category;
import com.exams.system.app.models.domain.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size( min = 5 )
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Integer maxPoints;
    @NotNull
    private Integer numberQuestions;
    private boolean status = false;
    @ManyToOne( fetch = EAGER )
    private Category category;
    @OneToMany( mappedBy = "questionnaire", fetch = EAGER, cascade = ALL )
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();
}