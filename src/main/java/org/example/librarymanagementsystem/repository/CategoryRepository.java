package org.example.librarymanagementsystem.repository;

import org.example.librarymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
