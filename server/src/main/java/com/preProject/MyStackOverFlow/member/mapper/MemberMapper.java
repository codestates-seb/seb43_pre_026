package com.preProject.MyStackOverFlow.member.mapper;

import com.preProject.MyStackOverFlow.member.dto.MemberAnswerDto;
import com.preProject.MyStackOverFlow.member.dto.MemberBoardDto;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPutDtoToMember(MemberDto.Put requestBody);
    MemberDto.Response memberToMemberResponse(Member member);

    default MemberDto.MemberResponse memberToMemberResponse2(Member member) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return MemberDto.MemberResponse.builder()
                .memberName(member.getMemberName())
                .memberAnswers(member.getAnswers().stream()
                        .filter(answer -> answer.isAnswerCheck() && answer.getParent() == null)
                        .map(memberAnswer -> MemberAnswerDto.Response.builder()
                                .boardId(memberAnswer.getBoard().getBoardId())
                                .title(memberAnswer.getBoard().getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .memberBoards(member.getBoards().stream()
                        .map(memberBoard -> MemberBoardDto.Response.builder()
                            .boardId(memberBoard.getBoardId())
                            .title(memberBoard.getTitle())
                            .build())
                        .collect(Collectors.toList()))
                .memberDay(ChronoUnit.DAYS.between(member.getCreatedAt() , currentDateTime)+ 1)
                .pngUrl(url)
                .build();
    }

    default MemberDto.MemberPut memberPutDtoToMember2(Member member) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return MemberDto.MemberPut.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberTitle(member.getMemberTitle())
                .memberLink(member.getMemberLink())
                .memberDescription(member.getMemberDescription())
                .memberPassword(member.getMemberPassword())
                .memberDay(ChronoUnit.DAYS.between(member.getCreatedAt() , currentDateTime)+ 1)
                .build();
    }
}
