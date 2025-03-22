package lk.ijse.voaestheticlounge.dto;

public class OrderDetailDTO {

        private Long id;  // Order Detail ID
        private Long orderId;  // Order ID the product is associated with
        private Long productId;  // Product ID that is part of the order
        private int quantity;  // Quantity of the product in the order
        private Double price;  // Price of a single product in the order

        // Default constructor
        public OrderDetailDTO() {}

        // Constructor to map from OrderDetail entity
        public OrderDetailDTO(Long id, Long orderId, Long productId, int quantity, Double price) {
            this.id = id;
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
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

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }


