package com.badalcode.blog.blogapi.services;

import com.badalcode.blog.blogapi.payload.categoryDto;

import java.util.List;

public interface categoryServices {

    //create
    categoryDto createCategory(categoryDto categorydto);

    //update
    categoryDto updateCategory(categoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get
    categoryDto getCategory(Integer categoryId);

    //get All
    List<categoryDto> getCategories();

}
