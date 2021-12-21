package me.junsu.demojpastudy.service;

import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.Member;
import me.junsu.demojpastudy.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Long save(Member member) {
        Member save = memberRepository.save(member);
        return save.getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    public void deleteById(Long id) { memberRepository.deleteById(id);}
}
