package domain;

public class Event {
    private int id;
    private String name;
    private String description;
    private String date;
    private String venue;
    private String category;
    private EventStatus status;
    private int adminId;

    public Event() {
    }

    public Event(int id, String name, String description, String date, String venue,
                 String category, EventStatus status, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.category = category;
        this.status = status;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }


    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}