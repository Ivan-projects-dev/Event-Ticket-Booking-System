package service;
import domain.Event;
import domain.EventStatus;
import persistence.EventRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final EventRepository eventRepository = new EventRepository();

    public Event createEvent(String name, String description, String dateTime, String venue, String category, String adminId) {
        String now = LocalDateTime.now().toString();
        Event event = new Event(null, name, description, dateTime, venue, category, EventStatus.DRAFT, adminId, now);
        eventRepository.save(event);
        return event;
    }

    public List<Event> listActiveEvents() {
        return eventRepository.findAll().stream().filter(e -> e.getStatus() == EventStatus.ACTIVE).collect(Collectors.toList());
    }

    public Event updateEvent(String eventId, String name, String description, String dateTime, String venue, String category) {
        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new IllegalArgumentException("Event not found: " + eventId);
        }
        if (name != null && !name.isBlank())
            event.setName(name);
        if (description != null) 
            event.setDescription(description);
        if (dateTime != null && !dateTime.isBlank()) 
            event.setDateTime(dateTime);
        if (venue != null && !venue.isBlank())      
            event.setVenue(venue);
        if (category != null && !category.isBlank()) 
            event.setCategory(category);
        eventRepository.save(event);
        return event;
    }

    public void publishEvent(String eventId) {
        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new IllegalArgumentException("Event not found: " + eventId);
        }
        if (event.getStatus() != EventStatus.DRAFT) {
            throw new IllegalStateException("Only DRAFT events can be published.");
        }
        event.setStatus(EventStatus.ACTIVE);
        eventRepository.save(event);
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

    public List<Event> findByDateRange(String from, String to) {
        if (from == null || to == null || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("Both 'from' and 'to' dates are required.");
        }
        if (from.compareTo(to) > 0) {
            throw new IllegalArgumentException("'from' date must not be after 'to' date.");
        }
        return eventRepository.findByDateRange(from, to);
    }

    public List<Event> searchByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return eventRepository.findAll();
        }
        return eventRepository.findByKeyword(keyword);
    }
}