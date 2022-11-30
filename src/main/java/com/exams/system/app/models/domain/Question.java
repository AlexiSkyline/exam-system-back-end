package com.exams.system.app.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table( name = "questions" )
@Getter @Setter
public class Question {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank
    @Column( length = 5000 )
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @NotBlank
    private String answer;
    @Transient
    private String userAnswer;
    @ManyToOne( fetch = EAGER )
    private Questionnaire questionnaire;
}