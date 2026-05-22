package service;

import domain.Ticket;
import persistence.TicketRepository;
import java.time.LocalDateTime;
import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepository = new TicketRepository();

    public Ticket createTicket(String eventId, String category, double price, String seatNumber) {
        String now = LocalDateTime.now().toString();
        Ticket ticket = new Ticket(null, eventId, category, price, seatNumber, true, now);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> listAvailableTickets(String eventId) {
        return ticketRepository.findAvailableByEventId(eventId);
    }

    public List<Ticket> listByEventId(String eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public void reserveTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found: " + ticketId);
        }

        if (!ticket.isAvailability()) {
            throw new IllegalStateException("Ticket is not available: " + ticketId);
        }

        ticketRepository.setAvailability(ticketId, false);
    }

    public void releaseTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found: " + ticketId);
        }

        ticketRepository.setAvailability(ticketId, true);
    }

    public Ticket findById(String id) {
        return ticketRepository.findById(id);
    }
}
