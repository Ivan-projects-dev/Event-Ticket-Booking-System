package service;

import domain.Booking;
import domain.BookingStatus;
import domain.BookingTicket;
import domain.Ticket;
import persistence.BookingRepository;
import persistence.BookingTicketRepository;
import persistence.TicketRepository;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final TicketRepository ticketRepository = new TicketRepository();

    public Booking createBooking(String userId, List<String> ticketIds) {
        if (ticketIds == null || ticketIds.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket must be selected.");
        }

        // Verify all tickets exist and are available
        for (String ticketId : ticketIds) {
            Ticket ticket = ticketRepository.findById(ticketId);

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket not found: " + ticketId);
            }

            if (!ticket.isAvailability()) {
                throw new IllegalStateException("Ticket is not available: " + ticketId);
            }
        }

        // Calculate total amount
        double total = 0;
        for (String ticketId : ticketIds) {
            total += ticketRepository.findById(ticketId).getPrice();
        }

        // Create the booking
        String now = LocalDateTime.now().toString();
        String confirmationCode = "BOOK-" + System.currentTimeMillis();
        Booking booking = new Booking(null, userId, now, total, BookingStatus.PENDING, confirmationCode, now);
        bookingRepository.save(booking);

        // Link tickets to booking and mark them unavailable
        for (String ticketId : ticketIds) {
            bookingTicketRepository.save(new BookingTicket(booking.getId(), ticketId));
            ticketRepository.setAvailability(ticketId, false);
        }

        return booking;
    }

    public Booking findById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> findByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
}
