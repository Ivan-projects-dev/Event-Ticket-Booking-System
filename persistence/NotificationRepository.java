package persistence;
import domain.Notification;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationRepository {
    private static final String FILE_PATH = "data/notifications.json";

    public List<Notification> findAll() {
        return JsonStorage.readList(FILE_PATH, Notification.class);
    }

    public Notification findById(int id) {
        List<Notification> notifications = findAll();

        for (Notification notification : notifications) {
            if (notification.getId() == id) {
                return notification;
            }
        }

        return null;
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

    public void save(Notification notification) {
        List<Notification> notifications = findAll();

        if (notification.getId() == 0) {
            notification.setId(generateNextId(notifications));
            notifications.add(notification);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < notifications.size(); i++) {
                if (notifications.get(i).getId() == notification.getId()) {
                    notifications.set(i, notification);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                notifications.add(notification);
            }
        }
        JsonStorage.writeList(FILE_PATH, notifications);
    }

    public void markAsRead(int notificationId) {
        Notification notification = findById(notificationId);

        if (notification == null) {
            return;
        }
        notification.setRead(true);
        save(notification);
    }

    public void deleteById(int id) {
        List<Notification> notifications = findAll();
        notifications.removeIf(notification -> notification.getId() == id);
        JsonStorage.writeList(FILE_PATH, notifications);
    }

    private int generateNextId(List<Notification> notifications) {
        int maxId = 0;

        for (Notification notification : notifications) {
            if (notification.getId() > maxId) {
                maxId = notification.getId();
            }
        }

        return maxId + 1;
    }
}