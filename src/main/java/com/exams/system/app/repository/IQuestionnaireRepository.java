package com.exams.system.app.repository;

import com.exams.system.app.models.domain.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findByCategoryId( Long id );
    List<Questionnaire> findByStatus( Boolean status );
    List<Questionnaire> findByCategoryIdAndStatus( Long id, Boolean status );
}