package com.preProject.MyStackOverFlow.member.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.preProject.MyStackOverFlow.awsS32.StorageService;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.response.SingleResponseDto;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.net.URL;

@CrossOrigin
@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {

    private final AmazonS3 s3Client;
    private final MemberService memberService;
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberMapper memberMapper;
    private final StorageService service;



    public MemberController(MemberMapper memberMapper,
                           MemberService memberService,
                            StorageService service,
                            AmazonS3 s3Client) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;
        this.service = service;
        this.s3Client = s3Client;

    }

    // 회원 정보 등록
    @PostMapping("")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {

        Member member = memberMapper.memberPostDtoToMember(requestBody);

        Member createdMember = memberService.createMember(member);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    // 한명의 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        URL url = s3Client.getUrl("mystackoverflows", Long.toString(memberId));
        String urltext = ""+url;

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse2(member, urltext)), HttpStatus.OK);
    }

    // 회원 정보 수정
    @PutMapping("/{member-id}")
    public ResponseEntity putMember(
            @PathVariable("member-id") @Positive long memberId,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "memberLink") String memberLink) {

        service.deleteFile(Long.toString(memberId));
        MemberDto.Put requestBody = new MemberDto.Put();
        requestBody.setMemberLink(memberLink);
        requestBody.setMemberId(memberId);
        service.uploadFile(file, memberId);

        Member member = memberService.updateMember(memberMapper.memberPutDtoToMember(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberPutDtoToMember2(member)), HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


