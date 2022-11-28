package com.exams.system.app.repository;

import com.exams.system.app.models.Question;
import com.exams.system.app.models.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuestionnaire( Questionnaire Questionnaire );
}