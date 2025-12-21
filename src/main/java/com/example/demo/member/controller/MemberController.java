package com.example.demo.member.controller;

import com.example.demo.member.dto.requestdto.LoginRequestDto;
import com.example.demo.member.dto.requestdto.MemberRequestDto;
import com.example.demo.member.dto.responsedto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.generic.response.commonResponse;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/member")

public class MemberController {
    private final MemberService memberService;

    //1. Add User
    @PostMapping("/createMember")
    public String createMember(@Valid @RequestBody MemberRequestDto memberRequestDto){
        Member member = memberService.createMember(memberRequestDto);
        return "user Inserted:"+member.getName();
    }

    //2. Get all Users
    @GetMapping("/getAllMembers")
    public commonResponse getAllMembers(){
        return new commonResponse(false, "Users fetched successfully",memberService.getAllMembers());
    }

    //3. Get user by ID
    @GetMapping("/getMemberById/{id}")
    public ResponseEntity<commonResponse> getMemberById(@PathVariable @NotBlank(message = "User ID cannot be blank")
            @Pattern(
                    regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                    message = "Invalid UUID format")
            String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(new commonResponse(false, "User fetched successfully",
                        memberService.getMemberById(uuid)));
    }

   /* @GetMapping("/getMemberById/{id}")
    public ResponseEntity<commonResponse> getMemberById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                new commonResponse(false, "User fetched successfully",
                        memberService.getMemberById(id))
        );
    }*/

    //3.Get User By Email
    @GetMapping("/getMemberByEmail/{email}")
    public ResponseEntity<commonResponse> getMemberByEmail(@PathVariable String email) {

        MemberResponseDto member = memberService.getMemberByEmail(email);
        return ResponseEntity.ok(new commonResponse(false, "User fetched successfully", member)
        );
    }

    //4. Update user details by id
    @PutMapping("/updateMemberById/{id}")
    public ResponseEntity<commonResponse> updateMemberById(@PathVariable UUID id, @RequestBody MemberRequestDto memberRequestDto){
       MemberResponseDto memberResponseDto= memberService.updateMemberById(id, memberRequestDto);
       return ResponseEntity.ok(new commonResponse(false, "User Updated Successfully", memberResponseDto));
    }

    //4.Update user by email
    @PutMapping("/updateMemberByEmail/{email}")
    public ResponseEntity<commonResponse> updateMemberByEmail(@PathVariable String email,
            @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto =
                memberService.updateMemberByEmail(email, memberRequestDto);
        return ResponseEntity.ok(new commonResponse(false, "User Updated Successfully", memberResponseDto)
        );
    }

    //5. Delete User by id
    @DeleteMapping("/deleteMemberById/{id}")
    public ResponseEntity<commonResponse> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(new commonResponse(false, "User Deleted successfully", "Deleted"));
    }

    //5.Delete User by Email
    @DeleteMapping("/deleteMemberByEmail/{email}")
    public ResponseEntity<commonResponse> deleteMemberByEmail(@PathVariable String email) {
        memberService.deleteMemberByEmail(email);
        return ResponseEntity.ok(new commonResponse(false, "User Deleted successfully", "Deleted"));
    }

    //6.Register
    @PostMapping("/register")
    public ResponseEntity<commonResponse> register(@RequestBody @Valid MemberRequestDto dto) {
        MemberResponseDto register = memberService.register(dto);
        return ResponseEntity.ok(new commonResponse(false, "Registration successful", register));
    }

    //7.Login
    @PostMapping("/login")
    public ResponseEntity<commonResponse> login(@RequestBody @Valid LoginRequestDto dto) {
        MemberResponseDto login = memberService.login(dto);
        return ResponseEntity.ok(new commonResponse(false, "Login successful", login));
    }

}
