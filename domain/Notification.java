package domain;

public class Notification {
    private int id;
    private int userId;
    private int bookingId;
    private String message;
    private String date;
    private boolean read;

    public Notification() {
    }

    public Notification(int id, int userId, int bookingId, String message,
                        String date, boolean read) {
        this.id = id;
        this.userId = userId;
        this.bookingId = bookingId;
        this.message = message;
        this.date = date;
        this.read = read;
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


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}