package com.example.demo.payment.service.serviceimpl;

import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.payment.dto.requestdto.PaymentRequestDto;
import com.example.demo.payment.dto.responsedto.PaymentResponseDto;
import com.example.demo.payment.entity.Payment;
import com.example.demo.payment.repository.PaymentRepository;
import com.example.demo.payment.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RazorpayClient razorpayClient;


    //Create Payment
    @Override
    public Payment createPayment(PaymentRequestDto paymentRequestDto) throws RazorpayException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",paymentRequestDto.getAmount());
       jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_"+System.currentTimeMillis());

        Order order = razorpayClient.orders.create(jsonObject);
        Payment payment=new Payment();
        payment.setRazorpayOrderId(order.get("id"));
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setStatus("CREATED");
        return paymentRepository.save(payment);
    }


    //Verify Payment
    @Override
    public String verifyPayment(PaymentResponseDto paymentResponseDto) {
        Payment payment = paymentRepository.findByRazorpayOrderId(paymentResponseDto.getRazorPayOrderId());
        if(payment==null){
            throw new RuntimeException("Order Not Found");
        }
        payment.setRazorpayPaymentId(paymentResponseDto.getRazorPaymentId());
        payment.setStatus("PAID");
        paymentRepository.save(payment);
        return "Payment verified successfully";
    }



/* To map payment to booking
    @Override
    public String verifyPayment(VerifyPaymentRequestDto dto) {

        // 1️⃣ Find payment by Razorpay order ID
        Payment payment = paymentRepository
                .findByRazorPayOrderId(dto.getRazorPayOrderId());

        if (payment == null) {
            throw new RuntimeException("Payment order not found");
        }

     // 2️⃣ Find booking
        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 3️⃣ Update payment
        payment.setRazorPaymentId(dto.getRazorPaymentId());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(booking);

        // 4️⃣ Update booking
        booking.setStatus("CONFIRMED");

        // 5️⃣ Save
        bookingRepository.save(booking);
        paymentRepository.save(payment);

        return "Payment verified & booking confirmed";
    }*/

}
