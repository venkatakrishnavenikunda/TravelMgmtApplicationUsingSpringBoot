package com.example.demo.booking.repository;

import com.example.demo.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByMember_Id(UUID memberId);

    //Optional <Booking> findByMember(String memberId);

    Optional<Booking> findByBookingId(String bookingId);

}