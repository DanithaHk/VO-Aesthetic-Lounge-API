package lk.ijse.voaestheticlounge.dto;


import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private double amount;
    private String method; // CREDIT_CARD, PAYPAL, BANK_TRANSFER
    private LocalDateTime paymentDate;

    public PaymentDTO(String method, Long id, Long bookingId, double amount, LocalDateTime paymentDate) {
        this.method = method;
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
