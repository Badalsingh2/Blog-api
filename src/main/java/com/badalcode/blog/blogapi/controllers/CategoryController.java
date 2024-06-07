package com.badalcode.blog.blogapi.controllers;

import com.badalcode.blog.blogapi.payload.ApiResponse;
import com.badalcode.blog.blogapi.payload.categoryDto;
import com.badalcode.blog.blogapi.services.categoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    categoryServices categoryservices;
    //create
    @PostMapping("/")
    public ResponseEntity<categoryDto> createCategory(@RequestBody categoryDto categoryDto){
        categoryDto createCategory = this.categoryservices.createCategory(categoryDto);
        return new ResponseEntity<categoryDto>(createCategory, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{catId}")
    public ResponseEntity<categoryDto> updateCategory(@RequestBody categoryDto categoryDto, @PathVariable Integer catId){
        categoryDto updatedCategory = this.categoryservices.updateCategory(categoryDto,catId);
        return new ResponseEntity<categoryDto>(updatedCategory, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId){
        this.categoryservices.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",false), HttpStatus.OK);

    }
    //getId
    @GetMapping("/{catId}")
    public ResponseEntity<categoryDto> getCategory( @PathVariable Integer catId){
        categoryDto gotCategory = this.categoryservices.getCategory(catId);
        return new ResponseEntity<categoryDto>(gotCategory, HttpStatus.OK);

    }
    //get ALl
    @GetMapping("/")
    public ResponseEntity<List<categoryDto>> getCategories(){
        List<categoryDto> categories =this.categoryservices.getCategories();
        return ResponseEntity.ok(categories);
    }
}
