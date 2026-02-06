package org.example.librarymanagementsystem.service;

import org.example.librarymanagementsystem.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAll();

    Member save(Member member);

    Optional<Member> findById(Integer id);

    void deleteById(int id);


}
