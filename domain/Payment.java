package domain;

public class BookingTicket {
    private int bookingId;
    private int ticketId;

    public BookingTicket() {
    }

    public BookingTicket(int bookingId, int ticketId) {
        this.bookingId = bookingId;
        this.ticketId = ticketId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}