package service;

import domain.Booking;
import domain.BookingStatus;
import domain.Notification;
import domain.Payment;
import domain.PaymentMethod;
import domain.PaymentStatus;
import persistence.BookingRepository;
import persistence.NotificationRepository;
import persistence.PaymentRepository;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {
    private final PaymentRepository paymentRepository = new PaymentRepository();
    private final BookingRepository bookingRepository = new BookingRepository();
    private final NotificationRepository notificationRepository = new NotificationRepository();

    public Payment pay(String bookingId, PaymentMethod method) {
        Booking booking = bookingRepository.findById(bookingId);

        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Booking is not in PENDING state.");
        }

        // Create a successful payment
        String now = LocalDateTime.now().toString();
        Payment payment = new Payment(null, bookingId, booking.getTotalAmount(),
                method, now, PaymentStatus.SUCCESS, now);
        paymentRepository.save(payment);

        // Confirm the booking
        bookingRepository.updateStatus(bookingId, BookingStatus.CONFIRMED);

        // Notify the user
        String message = "Your booking " + booking.getConfirmationCode()
                + " is confirmed. Total paid: " + booking.getTotalAmount();
        Notification notification = new Notification(null, booking.getUserId(), bookingId,
                "PAYMENT_SUCCESS", message, now, now);
        notificationRepository.save(notification);

        return payment;
    }

    public List<Payment> findByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
}
