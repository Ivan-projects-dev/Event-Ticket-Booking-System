package persistence;
import domain.BookingTicket;
import java.util.List;
import java.util.stream.Collectors;

public class BookingTicketRepository {
    private static final String FILE_PATH = "data/booking_tickets.json";

    public List<BookingTicket> findAll() {
        return JsonStorage.readList(FILE_PATH, BookingTicket.class);
    }

    public List<BookingTicket> findByBookingId(String bookingId) {
        List<BookingTicket> records = findAll();
        return records.stream().filter(bt -> bt.getBookingId() != null && bt.getBookingId().equals(bookingId)).collect(Collectors.toList());
    }

    public List<BookingTicket> findByTicketId(String ticketId) {
        List<BookingTicket> records = findAll();
        return records.stream().filter(bt -> bt.getTicketId() != null && bt.getTicketId().equals(ticketId)).collect(Collectors.toList());
    }

    public boolean exists(String bookingId, String ticketId) {
        List<BookingTicket> records = findAll();

        for (BookingTicket bt : records) {
            if (bookingId.equals(bt.getBookingId()) && ticketId.equals(bt.getTicketId())) {
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

    public void deleteByBookingId(String bookingId) {
        List<BookingTicket> records = findAll();
        records.removeIf(bt -> bookingId.equals(bt.getBookingId()));
        JsonStorage.writeList(FILE_PATH, records);
    }

    public void deleteByTicketId(String ticketId) {
        List<BookingTicket> records = findAll();
        records.removeIf(bt -> ticketId.equals(bt.getTicketId()));
        JsonStorage.writeList(FILE_PATH, records);
    }
}