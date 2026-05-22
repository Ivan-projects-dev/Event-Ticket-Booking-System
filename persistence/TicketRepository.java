package persistence;
import domain.Ticket;
import domain.TicketStatus;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepository extends GenericJsonRepository<Ticket> {
    private static final String FILE_PATH = "data/tickets.json";

    public TicketRepository() {
        super(FILE_PATH, Ticket.class);
    }

    public List<Ticket> findByEventId(String eventId) {
        return findAll().stream()
                .filter(ticket -> eventId.equals(ticket.getEventId()))
                .collect(Collectors.toList());
    }

    public List<Ticket> findAvailableByEventId(String eventId) {
        return findAll().stream()
                .filter(ticket -> eventId.equals(ticket.getEventId()))
                .filter(ticket -> ticket.getStatus() == TicketStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public void setStatus(String ticketId, TicketStatus status) {
        Ticket ticket = findById(ticketId);

        if (ticket == null) {
            return;
        }
        ticket.setStatus(status);
        save(ticket);
    }
}
