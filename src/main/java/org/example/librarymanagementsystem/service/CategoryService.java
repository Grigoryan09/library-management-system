package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category);


}
