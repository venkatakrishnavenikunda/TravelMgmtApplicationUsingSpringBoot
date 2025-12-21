package com.example.demo.member.dto.requestdto;

import com.example.demo.member.validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberRequestDto {

    @NotNull @NotBlank
    private String name;
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    ) @UniqueEmail
    private String email;
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;
    @NotNull @NotBlank
    private String address;

    @Pattern(
            regexp = "^\\+91-[6-9][0-9]{9}$",
            message = "Phone number must be in format +91-XXXXXXXXXX"
    )
    private String phno;
}
