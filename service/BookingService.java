package service;
import domain.Booking;
import domain.BookingStatus;
import domain.BookingTicket;
import domain.Ticket;
import domain.TicketStatus;
import persistence.BookingRepository;
import persistence.BookingTicketRepository;
import persistence.TicketRepository;
import java.time.LocalDate;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final TicketRepository ticketRepository = new TicketRepository();

    public Booking createBooking(int userId, List<Integer> ticketIds) {
        if (ticketIds == null || ticketIds.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket must be selected.");
        }
        // Verify all tickets exist and are available
        for (int ticketId : ticketIds) {
            Ticket ticket = ticketRepository.findById(ticketId);

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket not found: " + ticketId);
            }
            if (ticket.getStatus() != TicketStatus.AVAILABLE) {
                throw new IllegalStateException("Ticket is not available: " + ticketId);
            }
        }

        // Calculate total amount
        double total = 0;
        for (int ticketId : ticketIds) {
            total += ticketRepository.findById(ticketId).getPrice();
        }
        // Create the booking
        String today = LocalDate.now().toString(),
            confirmationCode = "BOOK-" + System.currentTimeMillis();
        Booking booking = new Booking(0, userId, today, total, BookingStatus.PENDING, confirmationCode);
        bookingRepository.save(booking);
        // Link tickets to booking and reserve them
        for (int ticketId : ticketIds) {
            bookingTicketRepository.save(new BookingTicket(booking.getId(), ticketId));
            ticketRepository.updateStatus(ticketId, TicketStatus.RESERVED);
        }
        return booking;
    }

    public Booking findById(int id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findByUserId(int userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> findByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
}