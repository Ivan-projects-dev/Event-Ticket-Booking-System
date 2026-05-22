package domain;

public class BookingTicket {
    private String bookingId;   // FK → bookings.booking_id
    private String ticketId;    // FK → tickets.ticket_id

    public BookingTicket() {
    }

    public BookingTicket(String bookingId, String ticketId) {
        this.bookingId = bookingId;
        this.ticketId = ticketId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
