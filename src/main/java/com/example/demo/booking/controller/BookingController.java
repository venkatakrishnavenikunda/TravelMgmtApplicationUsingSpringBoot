package com.example.demo.booking.controller;

import com.example.demo.booking.dto.requestdto.BookingRequestDto;
import com.example.demo.booking.entity.Booking;
import com.example.demo.booking.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.generic.response.commonResponse;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;

    //Add Booking
    @PostMapping("/addBooking")
    public String addBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto){
        Booking booking = bookingService.addBooking(bookingRequestDto);
        return "Booking added Successfully";
    }

    //Get Booking details
    @GetMapping("/getAllBooking")
    public commonResponse getAllBooking(){
        return new commonResponse(false,"All bookings fetched Successfully",bookingService.getAllBooking());
    }

    @GetMapping("/getallBookings")
    public ResponseEntity<commonResponse> getAllBookings(){
        return ResponseEntity.ok(new commonResponse(false,"All Booking Fetched",bookingService.getAllBooking()));
    }

    //Get Booking details by User Id
    @GetMapping("/getBookingByUserid/{id}")
    public commonResponse getBookingsByUserId(@PathVariable @Positive(message = "User Id must be positive")UUID id){
        return new commonResponse(false, "Bookings fetched successfully for user", bookingService.getBookingByUser(id));
    }

    //Delete Booking
    @DeleteMapping("/deleteBooking/{id}")
    public commonResponse deleteBooking(@PathVariable @Positive(message = "Booking Id must be positive")Integer id){
        bookingService.deleteBooking(id);
        return new commonResponse(false,"Booking deleted successfully",null);
    }


    //update status
    @PutMapping("/updatebookingStatus/{id}")
    public commonResponse updateBookingStatus(@PathVariable @Positive(message = "Booking Id must be positive")Integer id, @RequestParam Booking.BookingStatus status){
        return new commonResponse(false, "Booking status updated successfully", bookingService.updateBookingStatus(id,status));
    }


}
