package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.models.domain.Category;
import com.exams.system.app.models.response.ResponseBody;
import com.exams.system.app.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/category" )
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping( "/" )
    public ResponseEntity<ResponseBody<Category>> saveCategory( @RequestBody Category category ) {
        return ResponseHandler.responseBuild( CREATED, "Category Created Successfully", this.categoryService.add( category )  );
    }

    @GetMapping( "/" )
    public ResponseEntity<?> getAllCategories() {
        return ResponseHandler.responseBuild( OK, "Requested All Category are given here", this.categoryService.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ResponseBody<Category>> getCategoryById( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Requested Category By ID are given here", this.categoryService.findById( id ) );
    }

    @PutMapping( "/" )
    public ResponseEntity<ResponseBody<Category>> updateCategory( @RequestBody Category category ) {
        return ResponseHandler.responseBuild( OK, "Category Update Successfully", this.categoryService.update( category ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<ResponseBody<Category>> deleteCategory( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Category Delete Successfully", this.categoryService.deleteById( id ) );
    }
}