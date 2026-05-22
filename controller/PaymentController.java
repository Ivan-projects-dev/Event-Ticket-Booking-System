package controller;

import domain.Payment;
import domain.PaymentMethod;
import dto.PaymentDto;
import service.PaymentService;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {
    private final PaymentService paymentService = new PaymentService();

    public PaymentDto pay(String bookingId, PaymentMethod method) {
        Payment payment = paymentService.pay(bookingId, method);
        return PaymentDto.from(payment);
    }

    public List<PaymentDto> getPaymentsByBooking(String bookingId) {
        return paymentService.findByBookingId(bookingId).stream()
                .map(PaymentDto::from)
                .collect(Collectors.toList());
    }
}
