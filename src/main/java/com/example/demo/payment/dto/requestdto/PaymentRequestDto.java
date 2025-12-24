package com.example.demo.payment.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {

    private Double amount;
    private  String bookingId;

  /*  //To map booking
    private String razorPayOrderId;
    private String razorPaymentId;
    private Integer bookingId;*/

}
