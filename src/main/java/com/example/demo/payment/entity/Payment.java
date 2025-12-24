package com.example.demo.payment.entity;

import com.example.demo.booking.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String razorpayOrderId;
    private String razorpayPaymentId;

    private Double amount;

    //@Column(nullable =false)
    private String status;

    @ManyToOne
    @JoinColumn(name="booking-id")
    @JsonIgnore
    private Booking booking;
}
