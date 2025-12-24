package com.example.demo.member.entity;

import com.example.demo.booking.entity.Booking;
import com.example.demo.feedback.entity.Feedback;
import com.example.demo.location.entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //To generate Unique ID's like 550e8400-e29b-41d4-a716-446655440000

    private UUID id;

    private String name;
    private String email;
    private String password;
    private String address;
    private String phno;
    private final String paymentpolicy="This is payment policy...";


    @ManyToMany(mappedBy="members")
    private List<Location> locations;


    @Column(name="is_deleted")
    private Boolean isDeleted=false;

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Booking>booking;

}
