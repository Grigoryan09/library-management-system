package org.example.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Book;
import org.example.librarymanagementsystem.repository.BookRepository;
import org.example.librarymanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private  final BookRepository bookRepository;

        @Override
        public List<Book> findAll() {
           return bookRepository.findAll();
        }

        @Override
        public Book save(Book book) {
            return bookRepository.save(book);
        }

        @Override
        public void deleteById(int id) {
            bookRepository.deleteById(id);
        }

        @Override
        public List<Book> findByTitleContainingIgnoreCase(String search) {
            return bookRepository.findByTitleContainingIgnoreCase(search);
        }

        @Override
        public Optional<Book> findById(int bookId){
            return  bookRepository.findById(bookId);
        }
}
