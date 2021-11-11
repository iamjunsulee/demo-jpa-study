package me.junsu.demojpastudy.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.Address;
import me.junsu.demojpastudy.domain.Member;
import me.junsu.demojpastudy.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberVer1(@RequestBody Member member) {
        Long savedId = memberService.save(member);
        return new CreateMemberResponse(savedId);
        //api 요청 스펙에 맞춰서 별도의 DTO를 작성하는 것이 좋다.
        //Entity를 외부에 노출해선 안된다.
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberVer2(@RequestBody CreateMemberRequest request) {
        System.out.println(request.getAddress());
        Member member = new Member(request.getName(), request.getAddress());
        Long savedId = memberService.save(member);
        return new CreateMemberResponse(savedId);
    }

    @GetMapping("/api/v1/members")
    public List<Member> getMembers() {
        return memberService.findAll();
        //엔티티의 모든 값이 노출된다.
        // -> 노출하기 싫은 컬럼에 대해 @JsonIgnore 하면 노출 안시킬 수 있으나 엔티티에 프레젠테이션 로직이 들어가버린다.
        //용도에 따라 API가 다양하게 만들어질텐데 한 엔티티에 모든 API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
        //엔티티가 변경되면 API 스펙이 변해버린다.
    }

    @GetMapping("/api/v2/members")
    public Result<List<MemberDto>> getMembersV2() {
        List<Member> all = memberService.findAll();
        List<MemberDto> collect = all.stream()
                .map(m -> new MemberDto(m.getId(), m.getName(), m.getAddress())).collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
        //별도의 Result 클래스를 생성해서 사용하면 API 스펙 변경에 유연하다.
    }

    @GetMapping("/api/members/{name}")
    public Result<MemberDto> getMemberById(@PathVariable String name) {
        Member member = memberService.findByName(name);
        MemberDto memberDto = new MemberDto(member.getId(), member.getName(), member.getAddress());
        return new Result<>(1, memberDto);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private Long id;
        private String name;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private Address address;
    }
}
