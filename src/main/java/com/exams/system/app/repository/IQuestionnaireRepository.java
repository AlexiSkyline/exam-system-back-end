package com.exams.system.app.repository;

import com.exams.system.app.models.Category;
import com.exams.system.app.models.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findByCategory( Category category );
    List<Questionnaire> findByStatus( Boolean status );
    List<Questionnaire> findByCategoryAndStatus( Category category, Boolean status );
}