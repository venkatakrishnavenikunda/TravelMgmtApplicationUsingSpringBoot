package com.example.demo.travelpackage.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackageResponseDto {
   //private String packid="PK" + UUID.randomUUID().toString().substring(0,2);

    private String pakageName;
    private String packagePrice;
    private String packageDescription;
    private String offer;

    private UUID memberId;
    private UUID bookingId;


}
