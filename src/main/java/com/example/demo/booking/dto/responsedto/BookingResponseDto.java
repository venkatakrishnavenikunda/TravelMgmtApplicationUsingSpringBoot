package com.example.demo.booking.dto.responsedto;

import com.example.demo.booking.entity.Booking;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BookingResponseDto {
    private Integer id;
    private String bookingId;
    private String bookingStatus;
    private LocalDate startdate;
    private LocalDate enddate;
    private Integer memberId;
    private Integer locationId;

}
