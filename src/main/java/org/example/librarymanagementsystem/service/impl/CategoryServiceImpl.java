package org.example.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Category;
import org.example.librarymanagementsystem.repository.CategoryRepository;
import org.example.librarymanagementsystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private  final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
       return categoryRepository.save(category);
    }
}
