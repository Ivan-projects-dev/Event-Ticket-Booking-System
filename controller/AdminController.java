package controller;
import domain.Event;
import domain.User;
import dto.EventResponse;
import dto.UserDto;
import persistence.AdminRepository;
import service.EventService;
import service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();
    private final AdminRepository adminRepository = new AdminRepository();

    public List<UserDto> getAllUsers() {
        return userService.findAll().stream().map(u -> UserDto.from(u, adminRepository.isAdmin(u.getId()))).collect(Collectors.toList());
    }

    public List<UserDto> getAllAdmins() {
        return userService.findAll().stream().filter(u -> adminRepository.isAdmin(u.getId())).map(u -> UserDto.from(u, true)).collect(Collectors.toList());
    }

    public List<EventResponse> getAllEvents() {
        return eventService.findAll().stream().map(EventResponse::from).collect(Collectors.toList());
    }

    public EventResponse createEvent(String name, String description, String dateTime, String venue, String category, String adminId) {
        Event event = eventService.createEvent(name, description, dateTime, venue, category, adminId);
        return EventResponse.from(event);
    }

    public void cancelEvent(String eventId) {
        eventService.cancelEvent(eventId);
    }
}