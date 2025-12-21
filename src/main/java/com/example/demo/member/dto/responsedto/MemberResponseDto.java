package com.example.demo.member.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String address;
    private String phno;
}
