package service;

import domain.Booking;
import domain.BookingStatus;
import domain.BookingTicket;
import domain.Ticket;
import domain.TicketStatus;
import persistence.BookingRepository;
import persistence.BookingTicketRepository;
import persistence.TicketRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final TicketRepository ticketRepository = new TicketRepository();

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
        String now = LocalDateTime.now().toString();
        String confirmationCode = "BOOK-" + System.currentTimeMillis();
        Booking booking = new Booking(null, userId, now, total, BookingStatus.PENDING, confirmationCode, now);
        bookingRepository.save(booking);

        // Link each ticket to the booking and mark it reserved
        for (Ticket ticket : tickets) {
            bookingTicketRepository.save(new BookingTicket(booking.getId(), ticket.getId()));
            ticketRepository.setStatus(ticket.getId(), TicketStatus.RESERVED);
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
