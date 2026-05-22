package domain;

public class Ticket implements Identifiable {
    private String id;
    private String eventId;     // FK → events.event_id
    private String category;
    private double price;
    private String seatNumber;
    private boolean availability;
    private String createdAt;

    public Ticket() {
    }

    public Ticket(String id, String eventId, String category, double price,
                  String seatNumber, boolean availability, String createdAt) {
        this.id = id;
        this.eventId = eventId;
        this.category = category;
        this.price = price;
        this.seatNumber = seatNumber;
        this.availability = availability;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }


    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
