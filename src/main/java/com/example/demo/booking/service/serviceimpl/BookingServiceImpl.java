package com.example.demo.booking.service.serviceimpl;

import com.example.demo.booking.dto.requestdto.BookingRequestDto;
import com.example.demo.booking.dto.responsedto.BookingResponseDto;
import com.example.demo.booking.entity.Booking;
import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.booking.service.BookingService;
import com.example.demo.location.entity.Location;
import com.example.demo.location.repository.LocationRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.travelpackage.entity.TravelPackage;
import com.example.demo.travelpackage.repository.TravelPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private  final BookingRepository bookingRepository;

    private final LocationRepository locationRepository;

    private  final TravelPackageRepository travelPackageRepository;
    private final MemberRepository memberRepository;

    //Add Booking service implementation
    @Override
    public Booking addBooking(BookingRequestDto bookingRequestDto) {
        Booking booking=new Booking();
        /*BeanUtils.copyProperties(bookingRequestDto,booking);
        return bookingRepository.save(booking);*/

        booking.setStartdate(bookingRequestDto.getStartdate());
        booking.setEnddate(bookingRequestDto.getEnddate());
        booking.setBookingStatus(bookingRequestDto.getBookingStatus());

        //Set Member in booking table
        Member member = memberRepository.findById(bookingRequestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        booking.setMember(member);

        //Set Location in booking table
        Location location = locationRepository.findById(bookingRequestDto.getLocationId()).orElseThrow(()->new RuntimeException("Location Not Found"));
        booking.setLocation(location);

        //Set Package in booking table
        TravelPackage travelPackage = travelPackageRepository.findById(bookingRequestDto.getPackageId()).orElseThrow(() -> new RuntimeException("Package not found"));
        booking.setTravelPackage(travelPackage);

        return bookingRepository.save(booking);
    }

    //Get all bookings service implementation
    @Override
    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }


    //Get all bookings

    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<Booking> allBooking = bookingRepository.findAll();
        List<BookingResponseDto> bookingRespondDtoList = new ArrayList<>();
        allBooking.stream().forEach(booking -> {
            BookingResponseDto bookingRespondDto = new BookingResponseDto();
            BeanUtils.copyProperties(booking,bookingRespondDto);
            bookingRespondDtoList.add(bookingRespondDto);});
        return bookingRespondDtoList;

    }


    //Get bookings by user Id
    @Override
    public List<Booking> getBookingByUser(UUID userId) {
        return bookingRepository.findByMember_Id(userId);
    }


    //Update booking status by booking id
    @Override
    public Booking updateBookingStatus(Integer bookingId, Booking.BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        booking.setBookingStatus(status);
        return bookingRepository.save(booking);
    }

    //Delete booking by booking id
    @Override
    public void deleteBooking(Integer bookingId) {
        bookingRepository.deleteById(bookingId);
    }

}