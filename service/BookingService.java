package service;
import domain.Booking;
import domain.BookingStatus;
import domain.BookingTicket;
import domain.Notification;
import domain.Ticket;
import domain.TicketStatus;
import persistence.BookingRepository;
import persistence.BookingTicketRepository;
import persistence.NotificationRepository;
import persistence.TicketRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final TicketRepository ticketRepository = new TicketRepository();
    private final NotificationRepository notificationRepository = new NotificationRepository();

    public Booking createBooking(String userId, List<String> ticketIds) {
        if (ticketIds == null || ticketIds.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket must be selected.");
        }
        // Single pass: validate each ticket and accumulate total
        List<Ticket> tickets = new ArrayList<>();
        double total = 0;
        for (String ticketId : ticketIds) {
            Ticket ticket = ticketRepository.findById(ticketId);

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket not found: " + ticketId);
            }
            if (ticket.getStatus() != TicketStatus.AVAILABLE) {
                throw new IllegalStateException("Ticket is not available: " + ticketId);
            }
            tickets.add(ticket);
            total += ticket.getPrice();
        }
        // Create the booking
        String now = LocalDateTime.now().toString(),
            confirmationCode = "BOOK-" + System.currentTimeMillis();
        Booking booking = new Booking(null, userId, now, total, BookingStatus.PENDING, confirmationCode, now);
        bookingRepository.save(booking);
        // Link each ticket to the booking and mark it reserved
        for (Ticket ticket : tickets) {
            bookingTicketRepository.save(new BookingTicket(booking.getId(), ticket.getId()));
            ticketRepository.setStatus(ticket.getId(), TicketStatus.RESERVED);
        }
        return booking;
    }

    public Booking cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);

        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled.");
        }
        // Enforce 24-hour cancellation window
        LocalDateTime bookedAt = LocalDateTime.parse(booking.getBookingDate());
        long hoursElapsed = ChronoUnit.HOURS.between(bookedAt, LocalDateTime.now());
        if (hoursElapsed > 24) {
            throw new IllegalStateException("Cancellation window has expired. Bookings can only be cancelled within 24 hours of creation.");
        }
        // Cancel the booking
        bookingRepository.updateStatus(bookingId, BookingStatus.CANCELLED);
        // Restore each linked ticket to AVAILABLE
        List<BookingTicket> links = bookingTicketRepository.findByBookingId(bookingId);
        for (BookingTicket link : links) {
            ticketRepository.setStatus(link.getTicketId(), TicketStatus.AVAILABLE);
        }
        // Notify the user
        String now = LocalDateTime.now().toString(), message = "Your booking " + booking.getConfirmationCode() + " has been cancelled.";
        Notification notification = new Notification(null, booking.getUserId(), bookingId, "BOOKING_CANCELLED", message, now, now);
        notificationRepository.save(notification);
        booking.setStatus(BookingStatus.CANCELLED);
        return booking;
    }

    public Booking findById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking findByConfirmationCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Confirmation code must not be empty.");
        }
        return bookingRepository.findByConfirmationCode(code);
    }

    public List<Booking> findByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
}