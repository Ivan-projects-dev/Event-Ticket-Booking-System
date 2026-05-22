package persistence;
import domain.Ticket;
import domain.TicketStatus;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepository {
    private static final String FILE_PATH = "data/tickets.json";

    public List<Ticket> findAll() {
        return JsonStorage.readList(FILE_PATH, Ticket.class);
    }

    public Ticket findById(int id) {
        List<Ticket> tickets = findAll();

        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> findByEventId(int eventId) {
        List<Ticket> tickets = findAll();
        return tickets.stream().filter(ticket -> ticket.getEventId() == eventId).collect(Collectors.toList());
    }

    public List<Ticket> findAvailableByEventId(int eventId) {
        List<Ticket> tickets = findAll();
        return tickets.stream().filter(ticket -> ticket.getEventId() == eventId).filter(ticket -> ticket.getStatus() == TicketStatus.AVAILABLE).collect(Collectors.toList());
    }

    public void save(Ticket ticket) {
        List<Ticket> tickets = findAll();

        if (ticket.getId() == 0) {
            ticket.setId(generateNextId(tickets));
            tickets.add(ticket);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getId() == ticket.getId()) {
                    tickets.set(i, ticket);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                tickets.add(ticket);
            }
        }
        JsonStorage.writeList(FILE_PATH, tickets);
    }

    public void deleteById(int id) {
        List<Ticket> tickets = findAll();
        tickets.removeIf(ticket -> ticket.getId() == id);
        JsonStorage.writeList(FILE_PATH, tickets);
    }

    public void updateStatus(int ticketId, TicketStatus status) {
        Ticket ticket = findById(ticketId);

        if (ticket == null) {
            return;
        }
        ticket.setStatus(status);
        save(ticket);
    }

    private int generateNextId(List<Ticket> tickets) {
        int maxId = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getId() > maxId) {
                maxId = ticket.getId();
            }
        }

        return maxId + 1;
    }
}