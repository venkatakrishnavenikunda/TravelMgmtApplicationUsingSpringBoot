package com.example.demo.payment.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor

public class PaymentResponseDto {

    private Integer id;               // 1
    private String razorPayOrderId;   // 2
    private Double amount;            // 3
    private String status;            // 4
    private String bookingId;         // 5

    private String razorpayPaymentId;

    public PaymentResponseDto(Integer id, String razorpayOrderId, Double amount, String status, String bookingId) {
    }

    /*private String razorPayOrderId;
    private Double amount;
    private String status;
    private String razorPaymentId;
    private String bookingId;*/


}
