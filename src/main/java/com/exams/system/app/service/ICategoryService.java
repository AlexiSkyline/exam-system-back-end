package com.exams.system.app.service;

import com.exams.system.app.models.domain.Category;

import java.util.Set;

public interface ICategoryService {
    public Category add( Category category );
    public Category update( Category category );
    public Set<Category> findAll();
    public Category findById( Long id );
    public Category deleteById( Long id );
}