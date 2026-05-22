package service;
import domain.Notification;
import persistence.NotificationRepository;
import java.util.List;

public class NotificationService {
    private final NotificationRepository notificationRepository = new NotificationRepository();

    public Notification createNotification(int userId, int bookingId, String message, String date) {
        Notification notification = new Notification(0, userId, bookingId, message, date, false);
        notificationRepository.save(notification);
        return notification;
    }

    public List<Notification> getUnreadByUserId(int userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }

    public List<Notification> getByUserId(int userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getByBookingId(int bookingId) {
        return notificationRepository.findByBookingId(bookingId);
    }

    public void markAsRead(int notificationId) {
        notificationRepository.markAsRead(notificationId);
    }
}