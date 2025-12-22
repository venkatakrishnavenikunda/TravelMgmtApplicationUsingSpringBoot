package com.example.demo.booking.dto.requestdto;

import com.example.demo.booking.entity.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BookingRequestDto {

    //String bookingId="BK" + UUID.randomUUID().toString().substring(0,2);

    @NotNull
    private LocalDate startdate;
    @NotNull
    private LocalDate enddate;
    @NotNull
    private Booking.BookingStatus bookingStatus;
    @NotNull
    private UUID memberId;
    @NotNull
    private  Integer locationId;

}

