package persistence;
import domain.BookingTicket;
import java.util.List;
import java.util.stream.Collectors;

public class BookingTicketRepository {
    private static final String FILE_PATH = "data/booking_tickets.json";

    public List<BookingTicket> findAll() {
        return JsonStorage.readList(FILE_PATH, BookingTicket.class);
    }

    public List<BookingTicket> findByBookingId(int bookingId) {
        List<BookingTicket> records = findAll();
        return records.stream().filter(bt -> bt.getBookingId() == bookingId).collect(Collectors.toList());
    }

    public List<BookingTicket> findByTicketId(int ticketId) {
        List<BookingTicket> records = findAll();
        return records.stream().filter(bt -> bt.getTicketId() == ticketId).collect(Collectors.toList());
    }

    public boolean exists(int bookingId, int ticketId) {
        List<BookingTicket> records = findAll();

        for (BookingTicket bt : records) {
            if (bt.getBookingId() == bookingId && bt.getTicketId() == ticketId) {
                return true;
            }
        }
        return false;
    }

    public void save(BookingTicket bookingTicket) {
        List<BookingTicket> records = findAll();

        if (!exists(bookingTicket.getBookingId(), bookingTicket.getTicketId())) {
            records.add(bookingTicket);
            JsonStorage.writeList(FILE_PATH, records);
        }
    }

    public void deleteByBookingId(int bookingId) {
        List<BookingTicket> records = findAll();
        records.removeIf(bt -> bt.getBookingId() == bookingId);
        JsonStorage.writeList(FILE_PATH, records);
    }

    public void deleteByTicketId(int ticketId) {
        List<BookingTicket> records = findAll();
        records.removeIf(bt -> bt.getTicketId() == ticketId);
        JsonStorage.writeList(FILE_PATH, records);
    }
}