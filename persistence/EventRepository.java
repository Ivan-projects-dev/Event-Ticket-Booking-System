package persistence;
import domain.Event;
import java.util.List;

public class EventRepository extends GenericJsonRepository<Event> {
    private static final String FILE_PATH = "data/events.json";

    public EventRepository() {
        super(FILE_PATH, Event.class);
    }

    public List<Event> findByAdminId(int adminId) {
        List<Event> events = findAll();
        return events.stream().filter(event -> event.getAdminId() == adminId).toList();
    }

    public List<Event> findByCategory(String category) {
        List<Event> events = findAll();
        return events.stream().filter(event -> event.getCategory() != null).filter(event -> event.getCategory().equalsIgnoreCase(category)).toList();
    }
}
