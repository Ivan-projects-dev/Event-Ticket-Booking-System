package dto;

import domain.Payment;
import domain.PaymentMethod;
import domain.PaymentStatus;

public class PaymentDto {
    private String id;
    private String bookingId;
    private double amount;
    private PaymentMethod method;
    private String paymentDate;
    private PaymentStatus status;
    private String createdAt;

    public PaymentDto() {
    }

    public PaymentDto(String id, String bookingId, double amount, PaymentMethod method,
                      String paymentDate, PaymentStatus status, String createdAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static PaymentDto from(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getBookingId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }


    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }


    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
