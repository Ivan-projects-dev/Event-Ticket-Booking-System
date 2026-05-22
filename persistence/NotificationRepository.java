package persistence;
import domain.Notification;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationRepository extends GenericJsonRepository<Notification> {
    private static final String FILE_PATH = "data/notifications.json";

    public NotificationRepository() {
        super(FILE_PATH, Notification.class);
    }

    public List<Notification> findByUserId(String userId) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(n -> userId.equals(n.getUserId())).collect(Collectors.toList());
    }

    public List<Notification> findByBookingId(String bookingId) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(n -> bookingId.equals(n.getBookingId())).collect(Collectors.toList());
    }

    public List<Notification> findByType(String type) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(n -> type.equals(n.getType())).collect(Collectors.toList());
    }
}
