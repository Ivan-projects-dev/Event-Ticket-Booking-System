package service;

import domain.Notification;
import persistence.NotificationRepository;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationService {
    private final NotificationRepository notificationRepository = new NotificationRepository();

    public Notification createNotification(String userId, String bookingId,
                                           String type, String message) {
        String now = LocalDateTime.now().toString();
        Notification notification = new Notification(null, userId, bookingId, type, message, now, now);
        notificationRepository.save(notification);
        return notification;
    }

    public List<Notification> getByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getByBookingId(String bookingId) {
        return notificationRepository.findByBookingId(bookingId);
    }

    public List<Notification> getByType(String type) {
        return notificationRepository.findByType(type);
    }
}
