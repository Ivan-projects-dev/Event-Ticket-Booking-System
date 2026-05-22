package service;
import domain.Ticket;
import domain.TicketStatus;
import persistence.TicketRepository;
import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepository = new TicketRepository();

    public Ticket createTicket(int eventId, String category, double price, String seatNumber) {
        Ticket ticket = new Ticket(0, eventId, category, price, seatNumber, TicketStatus.AVAILABLE);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> listAvailableTickets(int eventId) {
        return ticketRepository.findAvailableByEventId(eventId);
    }

    public List<Ticket> listByEventId(int eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public void reserveTicket(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found: " + ticketId);
        }
        if (ticket.getStatus() != TicketStatus.AVAILABLE) {
            throw new IllegalStateException("Ticket is not available: " + ticketId);
        }
        ticketRepository.updateStatus(ticketId, TicketStatus.RESERVED);
    }

    public void sellTicket(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found: " + ticketId);
        }
        ticketRepository.updateStatus(ticketId, TicketStatus.SOLD);
    }

    public Ticket findById(int id) {
        return ticketRepository.findById(id);
    }
}