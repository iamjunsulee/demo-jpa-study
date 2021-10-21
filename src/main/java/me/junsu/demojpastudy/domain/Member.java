package me.junsu.demojpastudy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;            //회원번호

    private String name;        //회원명

    @Embedded
    private Address address;    //주소

    @Builder
    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    //연관관계의 주인이 아님을 명시
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
