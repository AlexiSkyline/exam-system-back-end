package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.Category;
import com.exams.system.app.repository.ICategoryRepository;
import com.exams.system.app.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public Category add( Category category ) {
        return this.categoryRepository.save( category );
    }

    @Override
    public Category update( Category category ) {
        return this.categoryRepository.save( category );
    }

    @Override
    public Set<Category> findAll() {
        return new LinkedHashSet<>( this.categoryRepository.findAll() );
    }

    @Override
    public Category findById( Long id ) {
        return this.categoryRepository.findById( id ).get();
    }

    @Override
    public Category deleteById( Long id ) {
        Category categoryFound = this.categoryRepository.findById( id ).get();
        this.categoryRepository.deleteById( id );
        return categoryFound;
    }
}