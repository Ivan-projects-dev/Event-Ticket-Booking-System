package persistence;
import domain.Event;
import java.util.List;

public class EventRepository extends GenericJsonRepository<Event> {
    private static final String FILE_PATH = "data/events.json";

    public EventRepository() {
        super(FILE_PATH, Event.class);
    }

    public List<Event> findByAdminId(String adminId) {
        List<Event> events = findAll();
        return events.stream().filter(event -> adminId.equals(event.getAdminId())).toList();
    }

    public List<Event> findByCategory(String category) {
        List<Event> events = findAll();
        return events.stream().filter(event -> event.getCategory() != null).filter(event -> event.getCategory().equalsIgnoreCase(category)).toList();
    }

    public List<Event> findByDateRange(String from, String to) {
        return findAll().stream().filter(event -> event.getDateTime() != null).filter(event -> event.getDateTime().compareTo(from) >= 0).filter(event -> event.getDateTime().compareTo(to) <= 0).toList();
    }

    public List<Event> findByKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return findAll().stream().filter(event ->
            (event.getName() != null && event.getName().toLowerCase().contains(lower)) ||
            (event.getDescription() != null && event.getDescription().toLowerCase().contains(lower))).toList();
    }
}