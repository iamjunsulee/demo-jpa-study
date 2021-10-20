package me.junsu.demojpastudy.repository;

import me.junsu.demojpastudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
