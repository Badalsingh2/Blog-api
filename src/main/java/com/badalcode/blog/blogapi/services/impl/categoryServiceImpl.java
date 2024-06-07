package com.badalcode.blog.blogapi.services.impl;

import com.badalcode.blog.blogapi.entities.Category;
import com.badalcode.blog.blogapi.exceptions.ResourceNotFoundException;
import com.badalcode.blog.blogapi.payload.categoryDto;
import com.badalcode.blog.blogapi.repositories.categoryRepo;
import com.badalcode.blog.blogapi.services.categoryServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Service
public class categoryServiceImpl implements categoryServices {
    @Autowired
    private categoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public categoryDto createCategory(categoryDto categorydto) {
        Category cat = this.modelMapper.map(categorydto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,categoryDto.class);
    }

    @Override
    public categoryDto updateCategory(categoryDto categoryDto, Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedcat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedcat,categoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        this.categoryRepo.delete(cat);

    }

    @Override
    public categoryDto getCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
        return this.modelMapper.map(cat,categoryDto.class);
    }

    @Override
    public List<categoryDto> getCategories() {

        List<Category> categories=this.categoryRepo.findAll();
        List<categoryDto> catDto= categories.stream().map((cat)->this.modelMapper.map(cat,categoryDto.class)).collect(Collectors.toList());
        return catDto;
    }
}
