package persistence;
import domain.Payment;
import domain.PaymentStatus;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepository extends GenericJsonRepository<Payment> {
    private static final String FILE_PATH = "data/payments.json";

    public PaymentRepository() {
        super(FILE_PATH, Payment.class);
    }

    public List<Payment> findByBookingId(String bookingId) {
        List<Payment> payments = findAll();
        return payments.stream().filter(payment -> bookingId.equals(payment.getBookingId())).collect(Collectors.toList());
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        List<Payment> payments = findAll();
        return payments.stream().filter(payment -> payment.getStatus() == status).collect(Collectors.toList());
    }
}