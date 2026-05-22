package service;

import domain.Event;
import domain.EventStatus;
import persistence.EventRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final EventRepository eventRepository = new EventRepository();

    public Event createEvent(String name, String description, String dateTime,
                             String venue, String category, String adminId) {
        String now = LocalDateTime.now().toString();
        Event event = new Event(null, name, description, dateTime, venue, category,
                EventStatus.ACTIVE, adminId, now);
        eventRepository.save(event);
        return event;
    }

    public List<Event> listActiveEvents() {
        return eventRepository.findAll().stream()
                .filter(e -> e.getStatus() == EventStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public void cancelEvent(String eventId) {
        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new IllegalArgumentException("Event not found: " + eventId);
        }

        event.setStatus(EventStatus.CANCELLED);
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(String id) {
        return eventRepository.findById(id);
    }

    public List<Event> findByAdminId(String adminId) {
        return eventRepository.findByAdminId(adminId);
    }

    public List<Event> findByCategory(String category) {
        return eventRepository.findByCategory(category);
    }
}
