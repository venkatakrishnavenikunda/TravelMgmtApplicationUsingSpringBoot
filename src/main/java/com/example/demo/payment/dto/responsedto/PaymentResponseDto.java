package com.example.demo.payment.dto.responsedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PaymentResponseDto {

    private String razorPayOrderId;
    private Double amount;
    private String status;
    private String razorPaymentId;

}
