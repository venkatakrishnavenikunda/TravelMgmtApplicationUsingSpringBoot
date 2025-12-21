package com.example.demo.booking.entity;

import com.example.demo.location.entity.Location;
import com.example.demo.member.entity.Member;
import com.example.demo.travelpackage.entity.TravelPackage;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Booking {

    public enum BookingStatus {
        BOOKED,
        CANCELLED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Booking.BookingStatus bookingStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String bookingId="BK" + UUID.randomUUID().toString().substring(0,2);
    private LocalDate startdate;
    private LocalDate enddate;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "booking")
    private List<TravelPackage> travelPackageList;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackage packages;

    //private String staus; //To map payment

}
