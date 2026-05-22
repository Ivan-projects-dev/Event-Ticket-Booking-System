package domain;

public class Booking implements Identifiable {
    private int id;
    private int userId;
    private String bookingDate;
    private double totalAmount;
    private BookingStatus status;
    private String confirmationCode;

    public Booking() {
    }

    public Booking(int id, int userId, String bookingDate, double totalAmount,
                   BookingStatus status, String confirmationCode) {
        this.id = id;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.confirmationCode = confirmationCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }


    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}