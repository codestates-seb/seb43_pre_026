package com.preProject.MyStackOverFlow.member.mapper;

import com.preProject.MyStackOverFlow.member.dto.MemberPostDto;
import com.preProject.MyStackOverFlow.member.dto.MemberPutDto;
import com.preProject.MyStackOverFlow.member.dto.MemberResponseDto;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPutDtoToMember(MemberPutDto memberPutDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
//    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
