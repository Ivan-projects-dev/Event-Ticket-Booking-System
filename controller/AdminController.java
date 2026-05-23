package controller;
import domain.BookingTicket;
import domain.Event;
import domain.Payment;
import domain.Ticket;
import domain.TicketStatus;
import domain.User;
import dto.EventResponse;
import dto.SalesSummary;
import dto.UserDto;
import persistence.AdminRepository;
import persistence.BookingTicketRepository;
import persistence.PaymentRepository;
import persistence.TicketRepository;
import service.EventService;
import service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();
    private final AdminRepository adminRepository = new AdminRepository();
    private final TicketRepository ticketRepository = new TicketRepository();
    private final BookingTicketRepository bookingTicketRepository = new BookingTicketRepository();
    private final PaymentRepository paymentRepository = new PaymentRepository();

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

    public SalesSummary getSalesSummary(String eventId) {
        List<Ticket> allTickets = ticketRepository.findByEventId(eventId);
        int ticketsTotal = allTickets.size(),
            ticketsAvailable = (int) allTickets.stream().filter(t -> t.getStatus() == TicketStatus.AVAILABLE).count(),
            ticketsSold = ticketsTotal - ticketsAvailable;

        // Sum revenue from confirmed payments linked to this event's tickets
        List<String> ticketIds = allTickets.stream().map(Ticket::getId).collect(Collectors.toList());
        double totalRevenue = bookingTicketRepository.findAll().stream().filter(bt -> ticketIds.contains(bt.getTicketId())).flatMap(bt -> paymentRepository.findByBookingId(bt.getBookingId()).stream()).mapToDouble(Payment::getAmount).sum();
        return new SalesSummary(eventId, ticketsSold, ticketsAvailable, ticketsTotal, totalRevenue);
    }
}