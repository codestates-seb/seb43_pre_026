package com.preProject.MyStackOverFlow.member.mapper;

import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPutDtoToMember(MemberDto.Put requestBody);

    MemberDto.Response memberToMemberResponse(Member member);
}
