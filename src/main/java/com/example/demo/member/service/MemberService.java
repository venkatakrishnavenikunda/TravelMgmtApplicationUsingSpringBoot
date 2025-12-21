package com.example.demo.member.service;

import com.example.demo.member.dto.requestdto.LoginRequestDto;
import com.example.demo.member.dto.requestdto.MemberRequestDto;
import com.example.demo.member.dto.responsedto.MemberResponseDto;
import com.example.demo.member.entity.Member;

import java.util.List;
import java.util.UUID;

public interface MemberService {

    Member createMember(MemberRequestDto member);

    List<MemberResponseDto> getAllMembers();

    MemberResponseDto getMemberById(UUID id);

    MemberResponseDto getMemberByEmail(String email);

    MemberResponseDto updateMemberById(UUID id, MemberRequestDto memberRequestDto);

    MemberResponseDto updateMemberByEmail(String email, MemberRequestDto memberRequestDto);

    void deleteMember(UUID id);

    void deleteMemberByEmail(String email);

    MemberResponseDto register(MemberRequestDto dto);

    MemberResponseDto login(LoginRequestDto dto);
}