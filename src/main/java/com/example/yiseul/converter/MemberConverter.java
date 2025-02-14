package com.example.yiseul.converter;

import com.example.yiseul.domain.Member;
import com.example.yiseul.dto.member.MemberCreateRequestDto;
import com.example.yiseul.dto.member.MemberCursorResponseDto;
import com.example.yiseul.dto.member.MemberPageResponseDto;
import com.example.yiseul.dto.member.MemberResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MemberConverter {

    public static Member convertMember(MemberCreateRequestDto createRequestDto) {

        return new Member(createRequestDto.name(), createRequestDto.age(), createRequestDto.hobby());
    }

    public static MemberResponseDto convertMemberResponseDto(Member member) {

        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getHobby(),
                member.getCreatedAt(),
                member.getCreatedBy()
        );
    }

    public static MemberPageResponseDto convertMemberPageResponseDto(Page<Member> page) {
        List<MemberResponseDto> memberResponseDtos = page.getContent().stream()
                .map(member -> convertMemberResponseDto(member))
                .collect(Collectors.toList());

        return new MemberPageResponseDto(
                memberResponseDtos,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }

    public static MemberCursorResponseDto convertMemberCursorResponseDto(List<Member> members, Long nextCursorId) {
        List<MemberResponseDto> memberResponseDtos = members.stream()
                .map(MemberConverter::convertMemberResponseDto)
                .collect(Collectors.toList());

        return new MemberCursorResponseDto(memberResponseDtos, nextCursorId);
    }
}
