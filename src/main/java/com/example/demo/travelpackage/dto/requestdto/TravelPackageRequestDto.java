package com.example.demo.travelpackage.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TravelPackageRequestDto {
    //private String packid="PK" + UUID.randomUUID().toString().substring(0,2);
    @NotNull @NotBlank
    private String packageName;
    @NotNull @NotBlank
    private String packagePrice;
    @NotNull
    private String packageDescription;
    @NotNull
    private String offer;

    private UUID memberId;
    private UUID bookingId;


}
