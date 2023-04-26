package com.preProject.MyStackOverFlow.member.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.preProject.MyStackOverFlow.awsS32.StorageService;
import com.preProject.MyStackOverFlow.helper.swagger.ResponseContent;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.mapper.MemberMapper;
import com.preProject.MyStackOverFlow.member.entity.Member;
import com.preProject.MyStackOverFlow.response.SingleResponseDto;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import com.preProject.MyStackOverFlow.utils.UriCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/members")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "MemberController", description = "회원 정보 API")
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
    @Operation(summary = "회원 정보 등록", description = "회원 정보를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원이 등록되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @PostMapping("")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {

        Member member = memberMapper.memberPostDtoToMember(requestBody);

        Member createdMember = memberService.createMember(member);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    // 한명의 회원 정보 조회
    @Operation(summary = "회원 정보 조회", description = "회원 식별자(memberId)에 해당하는 회원 정보를 조회합니다. \n" +
                ResponseContent.MEMBER_RESPONSE)
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다.",
            content = @Content(schema = @Schema(implementation = MemberDto.Response.class))),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        URL url = s3Client.getUrl("mystackoverflows", Long.toString(memberId));
        String urltext = ""+url;

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse2(member, urltext)), HttpStatus.OK);
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 식별자(memberId)에 해당하는 회원 정보를 수정합니다. \n" +
                "수정이 필요한 정보만 입력하시면 됩니다. \n" + ResponseContent.MEMBER_RESPONSE)
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
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
    @Operation(summary = "회원 정보 삭제", description = "회원 식별자(memberId)에 해당하는 회원 정보를 삭제합니다.")
    @Parameter(name = "member-id", description = "회원 식별자", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "회원 정보가 삭제되었습니다."),
            @ApiResponse(responseCode = "404", description = "정보를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우"),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.")
    })
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


