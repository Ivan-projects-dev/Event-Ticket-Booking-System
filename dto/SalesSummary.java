package dto;

public class SalesSummary {
    private String eventId;
    private int ticketsSold;
    private int ticketsAvailable;
    private int ticketsTotal;
    private double totalRevenue;

    public SalesSummary(String eventId, int ticketsSold, int ticketsAvailable, int ticketsTotal, double totalRevenue) {
        this.eventId = eventId;
        this.ticketsSold = ticketsSold;
        this.ticketsAvailable = ticketsAvailable;
        this.ticketsTotal = ticketsTotal;
        this.totalRevenue = totalRevenue;
    }

    public String getEventId() {
        return eventId; 
    }

    public int getTicketsSold() { 
        return ticketsSold;
    }

    public int getTicketsAvailable() { 
        return ticketsAvailable; 
    }

    public int getTicketsTotal() { 
        return ticketsTotal; 
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}