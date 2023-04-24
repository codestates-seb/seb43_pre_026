package com.preProject.MyStackOverFlow.member.controller;

import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
import com.preProject.MyStackOverFlow.member.dto.MemberPostDto;
import com.preProject.MyStackOverFlow.member.dto.MemberPutDto;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.member.service.MemberService1;
import com.preProject.MyStackOverFlow.response.SingleResponseDto;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {

    private static String MEMBER_DEFAULT_URL = "/members";
    private MemberService memberService;
    private MemberService1 memberService1;
    private MemberMapper mapper;

    // ================================================
    private final PasswordEncoder passwordEncoder;
// ================================================

    public MemberController(MemberService memberService, MemberMapper mapper, PasswordEncoder passwordEncoder,
                            MemberService1 memberService1) {
        this.memberService = memberService;
        this.mapper = mapper;
        // ================================================
        this.passwordEncoder = passwordEncoder;
        this.memberService1 = memberService1;
        // ================================================
    }
    // 회원 정보 등록
    @PostMapping("")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto requestBody) {

        Member member = mapper.memberPostDtoToMember(requestBody);
        member.setMemberRole("ROLE_USER");

        Member createdMember = memberService1.createMember(member);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

     // 한명의 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    @GetMapping("/process_login")
    public String getlogin() {
        return "test/login";
    }


    // 회원 정보 수정
    @PutMapping("/{member-id}")
    public ResponseEntity putMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPutDto requestBody) {
        requestBody.setMemberId(memberId);

        Member member =
                memberService.updateMember(mapper.memberPutDtoToMember(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


