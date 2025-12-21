package com.example.demo.location.dto.responsedto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LocationResponseDto {
    @Id
    @GeneratedValue
    private String cid= "LOC" + UUID.randomUUID().toString().substring(0,5);
    private String locationName;
}
