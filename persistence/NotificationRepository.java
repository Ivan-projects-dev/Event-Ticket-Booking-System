package persistence;
import domain.Notification;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationRepository extends GenericJsonRepository<Notification> {
    private static final String FILE_PATH = "data/notifications.json";

    public NotificationRepository() {
        super(FILE_PATH, Notification.class);
    }

    public List<Notification> findByUserId(int userId) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(notification -> notification.getUserId() == userId).collect(Collectors.toList());
    }

    public List<Notification> findByBookingId(int bookingId) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(notification -> notification.getBookingId() == bookingId).collect(Collectors.toList());
    }

    public List<Notification> findUnreadByUserId(int userId) {
        List<Notification> notifications = findAll();
        return notifications.stream().filter(notification -> notification.getUserId() == userId).filter(notification -> !notification.isRead()).collect(Collectors.toList());
    }

    public void markAsRead(int notificationId) {
        Notification notification = findById(notificationId);

        if (notification == null) {
            return;
        }
        notification.setRead(true);
        save(notification);
    }
}