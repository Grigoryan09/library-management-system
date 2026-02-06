package org.example.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Member;
import org.example.librarymanagementsystem.repository.MemberRepository;
import org.example.librarymanagementsystem.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private  final MemberRepository memberRepository;

    @Override
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member) ;
    }

    @Override
    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        memberRepository.deleteById(id);
    }
}
