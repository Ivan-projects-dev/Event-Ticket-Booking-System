package service;
import domain.Booking;
import domain.BookingStatus;
import domain.BookingTicket;
import domain.Notification;
import domain.Payment;
import domain.PaymentMethod;
import domain.PaymentStatus;
import domain.TicketStatus;
import persistence.BookingRepository;
import persistence.BookingTicketRepository;
import persistence.NotificationRepository;
import persistence.PaymentRepository;
import persistence.TicketRepository;
import java.time.LocalDate;
import java.util.List;

public class PaymentService {
    private final PaymentRepository paymentRepository = new PaymentRepository();
    private final BookingRepository bookingRepository = new BookingRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final TicketRepository ticketRepository = new TicketRepository();
    private final NotificationRepository notificationRepository = new NotificationRepository();

    public Payment pay(int bookingId, PaymentMethod paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId);

        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Booking is not in PENDING state.");
        }
        // Create a successful payment
        String today = LocalDate.now().toString();
        Payment payment = new Payment(0, bookingId, booking.getTotalAmount(), paymentMethod, today, PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
        // Confirm the booking
        bookingRepository.updateStatus(bookingId, BookingStatus.CONFIRMED);
        // Mark all reserved tickets as SOLD
        List<BookingTicket> links = bookingTicketRepository.findByBookingId(bookingId);
        for (BookingTicket link : links) {
            ticketRepository.updateStatus(link.getTicketId(), TicketStatus.SOLD);
        }
        // Notify the user
        String message = "Your booking " + booking.getConfirmationCode() + " is confirmed. Total paid: " + booking.getTotalAmount();
        Notification notification = new Notification(0, booking.getUserId(), bookingId, message, today, false);
        notificationRepository.save(notification);
        return payment;
    }

    public List<Payment> findByBookingId(int bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
}