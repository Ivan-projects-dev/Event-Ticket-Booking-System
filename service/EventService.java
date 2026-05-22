package service;
import domain.Event;
import domain.EventStatus;
import persistence.EventRepository;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final EventRepository eventRepository = new EventRepository();

    public Event createEvent(String name, String description, String date, String venue, String category, int adminId) {
        Event event = new Event(0, name, description, date, venue, category, EventStatus.ACTIVE, adminId);
        eventRepository.save(event);
        return event;
    }

    public List<Event> listActiveEvents() {
        return eventRepository.findAll().stream().filter(e -> e.getStatus() == EventStatus.ACTIVE).collect(Collectors.toList());
    }

    public void cancelEvent(int eventId) {
        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new IllegalArgumentException("Event not found: " + eventId);
        }
        event.setStatus(EventStatus.CANCELLED);
        eventRepository.save(event);
    }

    public Event findById(int id) {
        return eventRepository.findById(id);
    }

    public List<Event> findByAdminId(int adminId) {
        return eventRepository.findByAdminId(adminId);
    }

    public List<Event> findByCategory(String category) {
        return eventRepository.findByCategory(category);
    }
}