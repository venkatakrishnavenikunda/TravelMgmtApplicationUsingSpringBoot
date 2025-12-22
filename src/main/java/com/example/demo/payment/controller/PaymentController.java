package com.example.demo.payment.controller;

import com.example.demo.generic.response.commonResponse;
import com.example.demo.payment.dto.requestdto.PaymentRequestDto;
import com.example.demo.payment.dto.responsedto.PaymentResponseDto;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/payment")

public class PaymentController {
    private  final PaymentService service;


    //Add Payment
    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody PaymentRequestDto paymentRequestDto) throws Exception{
        return service.createPayment(paymentRequestDto);
    }

    //Verify Payment
    @PostMapping("/verifyPayment")
    public String verifyPayment(@RequestBody PaymentResponseDto paymentResponseDto) {
        return service.verifyPayment(paymentResponseDto);
    }
}