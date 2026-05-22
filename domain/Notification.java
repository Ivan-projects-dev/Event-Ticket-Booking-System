package domain;

public class Notification implements Identifiable {
    private String id;
    private String userId;      // FK → users.user_id
    private String bookingId;   // FK → bookings.booking_id
    private String type;        // e.g. "BOOKING_CONFIRMED", "PAYMENT_SUCCESS"
    private String message;
    private String sentDate;
    private String createdAt;

    public Notification() {
    }

    public Notification(String id, String userId, String bookingId, String type,
                        String message, String sentDate, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.bookingId = bookingId;
        this.type = type;
        this.message = message;
        this.sentDate = sentDate;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
