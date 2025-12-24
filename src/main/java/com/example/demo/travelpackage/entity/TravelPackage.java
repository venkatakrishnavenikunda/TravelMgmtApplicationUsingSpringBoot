package com.example.demo.travelpackage.entity;

import com.example.demo.booking.entity.Booking;
import com.example.demo.location.entity.Location;
import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Custom pakage id
    private String packid="PK" + UUID.randomUUID().toString().substring(0,2);

    private String packageName;
    private String packagePrice;
    private String packageDescription;
    private String offer;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    /*@ManyToOne
    @JoinColumn(name="booking-id")
    private Booking booking;
*/

    @OneToMany(mappedBy = "travelPackage")
    private List<Booking> bookingList;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


}
