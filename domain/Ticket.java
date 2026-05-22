package domain;

public class Ticket {
    private int id;
    private int eventId;
    private String category;
    private double price;
    private String seatNumber;
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(int id, int eventId, String category, double price,
                  String seatNumber, TicketStatus status) {
        this.id = id;
        this.eventId = eventId;
        this.category = category;
        this.price = price;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
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


    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}