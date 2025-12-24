package com.example.demo.booking.entity;

import com.example.demo.location.entity.Location;
import com.example.demo.member.entity.Member;
import com.example.demo.travelpackage.entity.TravelPackage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties({"member","location","travelPackage"})
public class Booking {

    public enum BookingStatus {
        BOOKED, CANCELLED
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
    @JsonIgnore
    private Member member;

//    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
//    private List<TravelPackage> travelPackageList;

    @ManyToOne
    @JoinColumn(name="package_id")
    private TravelPackage travelPackage;


    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;


    //private String staus; //To mapping payment module

}
