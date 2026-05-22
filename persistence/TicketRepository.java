package persistence;
import domain.Ticket;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepository extends GenericJsonRepository<Ticket> {
    private static final String FILE_PATH = "data/tickets.json";

    public TicketRepository() {
        super(FILE_PATH, Ticket.class);
    }

    public List<Ticket> findByEventId(String eventId) {
        List<Ticket> tickets = findAll();
        return tickets.stream().filter(ticket -> eventId.equals(ticket.getEventId())).collect(Collectors.toList());
    }

    public List<Ticket> findAvailableByEventId(String eventId) {
        List<Ticket> tickets = findAll();
        return tickets.stream().filter(ticket -> eventId.equals(ticket.getEventId())).filter(Ticket::isAvailability).collect(Collectors.toList());
    }

    public void setAvailability(String ticketId, boolean available) {
        Ticket ticket = findById(ticketId);

        if (ticket == null) {
            return;
        }
        ticket.setAvailability(available);
        save(ticket);
    }
}