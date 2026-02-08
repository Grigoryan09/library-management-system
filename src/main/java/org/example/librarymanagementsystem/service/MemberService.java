package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAll();

    Member save(Member member, MultipartFile multipartFile );

    Optional<Member> findById(Integer id);

    void deleteById(int id);


}
