package com.exams.system.app.service.impl;

import com.exams.system.app.models.Category;
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
        return this.categoryRepository.getReferenceById( id );
    }

    @Override
    public void deleteById( Long id ) {
        this.categoryRepository.deleteById( id );
    }
}