package controller;

import domain.Event;
import service.EventService;
import java.util.List;

public class EventController {
    private final EventService eventService = new EventService();

    public Event createEvent(String name, String description, String dateTime, String venue, String category, String adminId) {
        return eventService.createEvent(name, description, dateTime, venue, category, adminId);
    }

    public List<Event> listActiveEvents() {
        return eventService.listActiveEvents();
    }

    public List<Event> listByCategory(String category) {
        return eventService.findByCategory(category);
    }

    public List<Event> searchByKeyword(String keyword) {
        return eventService.searchByKeyword(keyword);
    }

    public List<Event> listByDateRange(String from, String to) {
        return eventService.findByDateRange(from, to);
    }

    public Event getEventById(String id) {
        return eventService.findById(id);
    }

    public Event updateEvent(String eventId, String name, String description,
                             String dateTime, String venue, String category) {
        return eventService.updateEvent(eventId, name, description, dateTime, venue, category);
    }

    public void publishEvent(String eventId) {
        eventService.publishEvent(eventId);
    }

    public void cancelEvent(String eventId) {
        eventService.cancelEvent(eventId);
    }
}