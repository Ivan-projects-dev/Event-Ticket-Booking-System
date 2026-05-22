package persistence;
import domain.Payment;
import domain.PaymentStatus;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepository {
    private static final String FILE_PATH = "data/payments.json";

    public List<Payment> findAll() {
        return JsonStorage.readList(FILE_PATH, Payment.class);
    }

    public Payment findById(int id) {
        List<Payment> payments = findAll();

        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findByBookingId(int bookingId) {
        List<Payment> payments = findAll();
        return payments.stream().filter(payment -> payment.getBookingId() == bookingId).collect(Collectors.toList());
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        List<Payment> payments = findAll();
        return payments.stream().filter(payment -> payment.getStatus() == status).collect(Collectors.toList());
    }

    public void save(Payment payment) {
        List<Payment> payments = findAll();

        if (payment.getId() == 0) {
            payment.setId(generateNextId(payments));
            payments.add(payment);
        }
        else {
            boolean updated = false;

            for (int i = 0; i < payments.size(); i++) {
                if (payments.get(i).getId() == payment.getId()) {
                    payments.set(i, payment);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                payments.add(payment);
            }
        }
        JsonStorage.writeList(FILE_PATH, payments);
    }

    public void updateStatus(int paymentId, PaymentStatus status) {
        Payment payment = findById(paymentId);

        if (payment == null) {
            return;
        }

        payment.setStatus(status);
        save(payment);
    }

    public void deleteById(int id) {
        List<Payment> payments = findAll();
        payments.removeIf(payment -> payment.getId() == id);
        JsonStorage.writeList(FILE_PATH, payments);
    }

    private int generateNextId(List<Payment> payments) {
        int maxId = 0;

        for (Payment payment : payments) {
            if (payment.getId() > maxId) {
                maxId = payment.getId();
            }
        }

        return maxId + 1;
    }
}