package controller;

import domain.Event;
import dto.EventResponse;
import service.EventService;
import java.util.List;
import java.util.stream.Collectors;

public class EventController {
    private final EventService eventService = new EventService();

    public EventResponse createEvent(String name, String description, String dateTime, String venue, String category, String adminId) {
        Event event = eventService.createEvent(name, description, dateTime, venue, category, adminId);
        return EventResponse.from(event);
    }

    public List<EventResponse> listActiveEvents() {
        return eventService.listActiveEvents().stream().map(EventResponse::from).collect(Collectors.toList());
    }

    public List<EventResponse> listByCategory(String category) {
        return eventService.findByCategory(category).stream().map(EventResponse::from).collect(Collectors.toList());
    }

    public List<EventResponse> searchByKeyword(String keyword) {
        return eventService.searchByKeyword(keyword).stream().map(EventResponse::from).collect(Collectors.toList());
    }

    public List<EventResponse> listByDateRange(String from, String to) {
        return eventService.findByDateRange(from, to).stream().map(EventResponse::from).collect(Collectors.toList());
    }

    public EventResponse getEventById(String id) {
        Event event = eventService.findById(id);

        if (event == null) {
            return null;
        }
        return EventResponse.from(event);
    }

    public EventResponse updateEvent(String eventId, String name, String description,
                                     String dateTime, String venue, String category) {
        Event event = eventService.updateEvent(eventId, name, description, dateTime, venue, category);
        return EventResponse.from(event);
    }

    public void publishEvent(String eventId) {
        eventService.publishEvent(eventId);
    }

    public void cancelEvent(String eventId) {
        eventService.cancelEvent(eventId);
    }
}