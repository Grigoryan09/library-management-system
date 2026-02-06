package org.example.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Category;
import org.example.librarymanagementsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @GetMapping("/category/add")
    public String addCategoryForm() {
        return "add-category";
    }

    @PostMapping("/category/add")
    public String saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return "redirect:/category";
    }




}
