package controller;
import domain.Payment;
import dto.BookingRequest;
import dto.PaymentDto;
import service.PaymentService;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {
    private final PaymentService paymentService = new PaymentService();

    /**
     * Pay for a pending booking.
     * The bookingId comes from the booking already created;
     * paymentMethod (e.g. "CARD") comes from the BookingRequest.
     */
    public PaymentDto pay(String bookingId, String paymentMethod) {
        Payment payment = paymentService.pay(bookingId, paymentMethod);
        return PaymentDto.from(payment);
    }

    public List<PaymentDto> getPaymentsByBooking(String bookingId) {
        return paymentService.findByBookingId(bookingId).stream().map(PaymentDto::from).collect(Collectors.toList());
    }
}