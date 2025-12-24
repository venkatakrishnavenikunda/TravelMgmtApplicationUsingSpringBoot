package com.example.demo.location.entity;

import com.example.demo.booking.entity.Booking;
import com.example.demo.member.entity.Member;
import com.example.demo.travelpackage.entity.TravelPackage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties({"members", "travelPackageList", "bookingList"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, updatable = false)
    private String locid; // auto-generated id

    @PrePersist
    public void prePersist() {
        if (locid == null) {
            this.locid = "LOC" + UUID.randomUUID().toString().substring(0,2);
        }
    }
    private String locationName;

    @ManyToMany
    @JoinTable(name = "location_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    @JsonIgnore
    private List<Member> members;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<TravelPackage> travelPackageList;

    @OneToMany(mappedBy = "location")
    private List<Booking> bookingList;
}