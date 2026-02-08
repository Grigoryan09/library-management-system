package org.example.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Book;
import org.example.librarymanagementsystem.model.Member;
import org.example.librarymanagementsystem.service.BookService;
import org.example.librarymanagementsystem.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/members")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("books", bookService.findAll());
        return "member";
    }

    @GetMapping("/members/add")
    public String addMemberForm() {
        return "add-member";
    }

    @PostMapping("/members/add")
    public String saveMember(@ModelAttribute Member member, @RequestParam("pic") MultipartFile multipartFile) {
        member.setRegistrationDate(LocalDateTime.now());
        memberService.save(member, multipartFile);
        return "redirect:/members";
    }

    @PostMapping("/members/borrow")
    public String borrowBook(@RequestParam("memberId") int memberId,
                             @RequestParam("bookId") int bookId,
                             @RequestParam("pic") MultipartFile multipartFile) {
        Member member = memberService.findById(memberId).orElseThrow();
        Book book = bookService.findById(bookId).orElseThrow();
        member.getBorrowedBooks().add(book);
        memberService.save(member, multipartFile);
        return "redirect:/members";
    }

    @GetMapping("/members/details")
    public String getMemberDetails(@RequestParam("id") int id, Model model) {

        Optional<Member> optionalMember = memberService.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            model.addAttribute("borrowedBooks", member.getBorrowedBooks());
            return "member-details";
        }

        return "redirect:/members";
    }

    @GetMapping("/members/delete")
    public String deleteMember(@RequestParam("id") int id) {
        memberService.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/members/details/delete")
    public String deleteBorrowedBook(@RequestParam("memberId") int memberId,
                                     @RequestParam("bookId") int bookId,
                                     @RequestParam("pic") MultipartFile multipartFile) {
        Optional<Member> optionalMember = memberService.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            List<Book> borrowedBooks = member.getBorrowedBooks();
            borrowedBooks.removeIf(book -> book.getId() == bookId);
            member.setBorrowedBooks(borrowedBooks);
            memberService.save(member, multipartFile);
        }
        return "redirect:/members/details?id=" + memberId;
    }

}
