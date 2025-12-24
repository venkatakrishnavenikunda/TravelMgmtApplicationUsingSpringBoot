package com.example.demo.payment.service;

import com.example.demo.payment.dto.requestdto.PaymentRequestDto;
import com.example.demo.payment.dto.responsedto.PaymentResponseDto;
import com.example.demo.payment.entity.Payment;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) throws RazorpayException;

    String verifyPayment(PaymentResponseDto paymentResponseDto);

    //String verifyPayment(String orderId, String paymentId, String signature);
}
