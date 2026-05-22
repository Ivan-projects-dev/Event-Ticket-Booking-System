package persistence;
import domain.Booking;
import domain.BookingStatus;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepository {
    private static final String FILE_PATH = "data/bookings.json";

    public List<Booking> findAll() {
        return JsonStorage.readList(FILE_PATH, Booking.class);
    }

    public Booking findById(int id) {
        List<Booking> bookings = findAll();

        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }

    public List<Booking> findByUserId(int userId) {
        List<Booking> bookings = findAll();
        return bookings.stream().filter(booking -> booking.getUserId() == userId).collect(Collectors.toList());
    }

    public List<Booking> findByStatus(BookingStatus status) {
        List<Booking> bookings = findAll();
        return bookings.stream().filter(booking -> booking.getStatus() == status).collect(Collectors.toList());
    }

    public void save(Booking booking) {
        List<Booking> bookings = findAll();

        if (booking.getId() == 0) {
            booking.setId(generateNextId(bookings));
            bookings.add(booking);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).getId() == booking.getId()) {
                    bookings.set(i, booking);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                bookings.add(booking);
            }
        }
        JsonStorage.writeList(FILE_PATH, bookings);
    }

    public void updateStatus(int bookingId, BookingStatus status) {
        Booking booking = findById(bookingId);

        if (booking == null) {
            return;
        }
        booking.setStatus(status);
        save(booking);
    }

    public void deleteById(int id) {
        List<Booking> bookings = findAll();
        bookings.removeIf(booking -> booking.getId() == id);
        JsonStorage.writeList(FILE_PATH, bookings);
    }

    private int generateNextId(List<Booking> bookings) {
        int maxId = 0;

        for (Booking booking : bookings) {
            if (booking.getId() > maxId) {
                maxId = booking.getId();
            }
        }
        return maxId + 1;
    }
}