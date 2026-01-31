package org.example.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;

import org.example.librarymanagementsystem.model.Book;
import org.example.librarymanagementsystem.repository.BookRepository;
import org.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final   CategoryRepository categoryRepository;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "book";
    }

    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String saveBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/details")
    public String bookDetails(@RequestParam int id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String search, Model model) {
        model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(search));
        return "book";
    }

}
