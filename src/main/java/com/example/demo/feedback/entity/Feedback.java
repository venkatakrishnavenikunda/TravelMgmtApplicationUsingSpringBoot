package com.example.demo.feedback.entity;

import com.example.demo.location.entity.Location;
import com.example.demo.member.entity.Member;
import com.example.demo.travelpackage.entity.TravelPackage;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String feedback;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackage travelPackage;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
