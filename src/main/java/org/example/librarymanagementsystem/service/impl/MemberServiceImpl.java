package org.example.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagementsystem.model.Member;
import org.example.librarymanagementsystem.repository.MemberRepository;
import org.example.librarymanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${library.management.upload.image.directory.path}")
    private String imageDirectoryPath;
    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member member, MultipartFile multipartFile) {
        if ( multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                member.setPictureName(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return memberRepository.save(member);
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
