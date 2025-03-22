package lk.ijse.voaestheticlounge.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDTO {


        private Long id;  // Order ID
        private Long userId;  // User ID who placed the order
        private LocalDate orderDate;  // The date and time when the order was placed
        private Double totalPrice;  // The total price of the order

        // Default constructor
        public OrderDTO() {}

        // Constructor to map from Order entity
        public OrderDTO(Long id, Long userId, LocalDate orderDate, Double totalPrice) {
            this.id = id;
            this.userId = userId;
            this.orderDate = orderDate;
            this.totalPrice = totalPrice;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }


