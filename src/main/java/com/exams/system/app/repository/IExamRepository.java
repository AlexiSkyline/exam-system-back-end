package com.exams.system.app.repository;

import com.exams.system.app.models.Category;
import com.exams.system.app.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCategory( Category category );
    List<Exam> findByStatus( Boolean status );
    List<Exam> findByCategoryAndStatus( Category category, Boolean status );
}