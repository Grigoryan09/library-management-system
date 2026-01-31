package org.example.librarymanagementsystem.repository;

import org.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);
}
