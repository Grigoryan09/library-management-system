package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {


    List<Book> findAll();

    Book save(Book book);

    void deleteById(int id);

    List<Book> findByTitleContainingIgnoreCase(String search);

    Optional<Book> findById(int bookId);
}

