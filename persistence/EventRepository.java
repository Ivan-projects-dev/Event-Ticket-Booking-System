package persistence;
import domain.Event;
import java.util.List;

public class EventRepository {
    private static final String FILE_PATH = "data/events.json";

    public List<Event> findAll() {
        return JsonStorage.readList(FILE_PATH, Event.class);
    }

    public Event findById(int id) {
        List<Event> events = findAll();

        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public List<Event> findByAdminId(int adminId) {
        List<Event> events = findAll();
        return events.stream().filter(event -> event.getAdminId() == adminId).toList();
    }

    public List<Event> findByCategory(String category) {
        List<Event> events = findAll();
        return events.stream().filter(event -> event.getCategory() != null).filter(event -> event.getCategory().equalsIgnoreCase(category)).toList();
    }

    public void save(Event event) {
        List<Event> events = findAll();

        if (event.getId() == 0) {
            event.setId(generateNextId(events));
            events.add(event);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getId() == event.getId()) {
                    events.set(i, event);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                events.add(event);
            }
        }

        JsonStorage.writeList(FILE_PATH, events);
    }

    public void deleteById(int id) {
        List<Event> events = findAll();
        events.removeIf(event -> event.getId() == id);
        JsonStorage.writeList(FILE_PATH, events);
    }

    private int generateNextId(List<Event> events) {
        int maxId = 0;

        for (Event event : events) {
            if (event.getId() > maxId) {
                maxId = event.getId();
            }
        }
        return maxId + 1;
    }
}