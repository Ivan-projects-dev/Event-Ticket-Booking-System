package controller;
import domain.Ticket;
import service.TicketService;
import java.util.List;

public class TicketController {
    private final TicketService ticketService = new TicketService();

    public Ticket createTicket(String eventId, String category, double price, String seatNumber) {
        return ticketService.createTicket(eventId, category, price, seatNumber);
    }

    public List<Ticket> listAvailableTickets(String eventId) {
        return ticketService.listAvailableTickets(eventId);
    }

    public List<Ticket> listAllTicketsByEvent(String eventId) {
        return ticketService.listByEventId(eventId);
    }

    public Ticket getTicketById(String id) {
        return ticketService.findById(id);
    }
}