package com.exams.system.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table( name = "questions" )
@Getter @Setter
public class Question {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( length = 5000 )
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    @ManyToOne( fetch = EAGER )
    private Exam exam;
}