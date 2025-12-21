package com.example.demo.booking.service;

import com.example.demo.booking.dto.requestdto.BookingRequestDto;
import com.example.demo.booking.entity.Booking;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking addBooking(BookingRequestDto bookingRequestDto);

    List<Booking> getAllBooking();

    List<Booking> getBookingByUser(UUID userId);


    void deleteBooking(Integer bookingId);

    Booking updateBookingStatus(Integer bookingId, Booking.BookingStatus status);

}