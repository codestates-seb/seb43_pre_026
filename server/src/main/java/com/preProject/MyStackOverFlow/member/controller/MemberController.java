package com.preProject.MyStackOverFlow.member.controller;

import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.response.SingleResponseDto;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/members")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberMapper memberMapper;



    public MemberController(MemberMapper memberMapper,
                           MemberService memberService) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;

    }

    // 회원 정보 등록
    @Operation(summary = "회원 정보 등록", description = "회원 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "회원이 등록되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PostMapping("")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {

        Member member = memberMapper.memberPostDtoToMember(requestBody);

        Member createdMember = memberService.createMember(member);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    // 한명의 회원 정보 조회
    @Operation(summary = "회원 정보 조회", description = "회원 식별자(memberId)에 해당하는 회원 정보를 조회합니다.")
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상 처리되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse2(member)), HttpStatus.OK);
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 식별자(memberId)에 해당하는 회원 정보를 수정합니다. \n" +
                "수정이 필요한 정보만 입력하시면 됩니다.")
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상 처리되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @PutMapping("/{member-id}")
    public ResponseEntity putMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Put requestBody) {
        requestBody.setMemberId(memberId);

        Member member = memberService.updateMember(memberMapper.memberPutDtoToMember(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberPutDtoToMember2(member)), HttpStatus.OK);
    }

    // 회원 정보 삭제
    @Operation(summary = "회원 정보 삭제", description = "회원 식별자(memberId)에 해당하는 회원 정보를 삭제합니다.")
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "회원 정보가 삭제되었습니다."),
            @ApiResponse(code = 404, message = "정보를 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 401, message = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(code = 403, message = "요청에 대한 권한이 없습니다.")
    })
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


