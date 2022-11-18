package com.exams.system.app.controller;

import com.exams.system.app.models.Category;
import com.exams.system.app.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/category" )
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping( "/" )
    public ResponseEntity<Category> saveCategory( @RequestBody Category category ) {
        return ResponseEntity.ok( this.categoryService.add( category ) );
    }

    @GetMapping( "/{id}" )
    public Category getCategoryById( @PathVariable Long id ) {
        return this.categoryService.findById( id );
    }

    @GetMapping( "/" )
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok( this.categoryService.findAll() );
    }

    @PutMapping( "/" )
    public Category updateCategory( @RequestBody Category category ) {
        return this.categoryService.update( category );
    }

    @DeleteMapping( "/{id}" )
    public void deleteCategory( @PathVariable Long id ) {
        this.categoryService.deleteById( id );
    }
}