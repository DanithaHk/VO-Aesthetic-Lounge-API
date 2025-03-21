package lk.ijse.voaestheticlounge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 255) // Limiting image URL size
    private String image;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    public Cart() {
    }

    public Cart(String productName, String image, int quantity, Long productId, Long userId, Long cartId) {
        this.productName = productName;
        this.image = image;
        this.quantity = quantity;
        this.productId = productId;
        this.userId = userId;
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
