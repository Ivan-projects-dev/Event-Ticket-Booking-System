package persistence;
import domain.Booking;
import domain.BookingStatus;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepository extends GenericJsonRepository<Booking> {
    private static final String FILE_PATH = "data/bookings.json";

    public BookingRepository() {
        super(FILE_PATH, Booking.class);
    }

    public List<Booking> findByUserId(String userId) {
        List<Booking> bookings = findAll();
        return bookings.stream().filter(booking -> userId.equals(booking.getUserId())).collect(Collectors.toList());
    }

    public List<Booking> findByStatus(BookingStatus status) {
        List<Booking> bookings = findAll();
        return bookings.stream().filter(booking -> booking.getStatus() == status).collect(Collectors.toList());
    }

    public Booking findByConfirmationCode(String code) {
        return findAll().stream().filter(b -> code.equals(b.getConfirmationCode())).findFirst().orElse(null);
    }

    public void updateStatus(String bookingId, BookingStatus status) {
        Booking booking = findById(bookingId);

        if (booking == null) {
            return;
        }
        booking.setStatus(status);
        save(booking);
    }
}