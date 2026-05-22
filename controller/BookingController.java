package controller;
import domain.Booking;
import dto.BookingRequest;
import service.BookingService;
import java.util.List;

public class BookingController {
    private final BookingService bookingService = new BookingService();

    public Booking createBooking(BookingRequest request) {
        return bookingService.createBooking(request.getUserId(), request.getTicketIds());
    }

    public Booking getBookingById(String id) {
        return bookingService.findById(id);
    }

    public List<Booking> getBookingsByUser(String userId) {
        return bookingService.findByUserId(userId);
    }
}