package com.example.demo.member.service.serviceimpl;

import com.example.demo.member.dto.requestdto.LoginRequestDto;
import com.example.demo.member.dto.requestdto.MemberRequestDto;
import com.example.demo.member.dto.responsedto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MemberServiceImpl  implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    //private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;


    //Add user service implementation
    @Override
    public Member createMember(MemberRequestDto memberRequestDto) {

        logger.info("Creating member with email {}", memberRequestDto.getEmail());

        Member m = new Member();
        BeanUtils.copyProperties(memberRequestDto, m);
        return memberRepository.save(m);

    }

    //Get all members service implementation
    @Override
    public List<MemberResponseDto> getAllMembers() {
        List<Member> allMembers = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        allMembers.stream().forEach(member -> {
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            BeanUtils.copyProperties(member, memberResponseDto);
            memberResponseDtoList.add(memberResponseDto);
        });
        return memberResponseDtoList;
    }


    //Get Member By id
    @Override
    public MemberResponseDto getMemberById(UUID id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Member not found with ID: " + id)
                );
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getAddress(), member.getPhno());
    }

    //Fetching user By Email
    @Override
    public MemberResponseDto getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found with email: " + email));
        return mapToDto(member);
    }

    // Helper method to convert entity to DTO
    private MemberResponseDto mapToDto(Member member) {
        MemberResponseDto dto = new MemberResponseDto();
        BeanUtils.copyProperties(member, dto);
        return dto;
    }


    //Update Member by Id
    @Override
    public MemberResponseDto updateMemberById(UUID id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found with ID: " + id));

        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setAddress(memberRequestDto.getAddress());
        member.setPhno(memberRequestDto.getPhno());

        Member updatedMember = memberRepository.save(member);

        return new MemberResponseDto(
                updatedMember.getId(),
                updatedMember.getName(),
                updatedMember.getEmail(),
                updatedMember.getAddress(),
                updatedMember.getPhno()
        );
    }

    @Override
    public MemberResponseDto updateMemberByEmail(String email, MemberRequestDto memberRequestDto) {

        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Member not found with email: " + email));

        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setAddress(memberRequestDto.getAddress());
        member.setPhno(memberRequestDto.getPhno());

        return mapToDto(memberRepository.save(member));
    }

    //Delete Member by Id
    @Override
    public void deleteMember(UUID id) {

        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not Found with ID:" + id));
        memberRepository.delete(member);
    }

    @Override
    public void deleteMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Member not found with email: " + email));

        memberRepository.delete(member);
    }


    @Override
    public MemberResponseDto register(MemberRequestDto dto) {

        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword()); // plain for now
        member.setAddress(dto.getAddress());
        member.setPhno(dto.getPhno());
        Member savedMember = memberRepository.save(member);
        return mapToDto(savedMember);
    }


    @Override
    public MemberResponseDto login(LoginRequestDto dto) {

        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!member.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return mapToDto(member);
    }
}
