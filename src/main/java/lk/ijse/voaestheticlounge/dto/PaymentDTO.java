package lk.ijse.voaestheticlounge.dto;


import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;  // Payment ID
    private Long orderId;  // Order ID associated with this payment
    private LocalDateTime paymentDate;  // Date and time of the payment
    private Double amount;  // Amount paid
    private String paymentMethod;  // Payment method (e.g., Credit Card, PayPal)

    // Default constructor
    public PaymentDTO() {}

    // Constructor to map from Payment entity
    public PaymentDTO(Long id, Long orderId, LocalDateTime paymentDate, Double amount, String paymentMethod) {
        this.id = id;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
