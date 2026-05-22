package controller;

import domain.Booking;
import service.BookingService;
import java.util.List;

public class BookingController {
    private final BookingService bookingService = new BookingService();

    public Booking createBooking(String userId, List<String> ticketIds) {
        return bookingService.createBooking(userId, ticketIds);
    }

    public Booking getBookingById(String id) {
        return bookingService.findById(id);
    }

    public Booking cancelBooking(String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    public List<Booking> getBookingsByUser(String userId) {
        return bookingService.findByUserId(userId);
    }
}
