package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.CartDTO;
import lk.ijse.voaestheticlounge.entity.Cart;

import java.util.List;

public interface CartService {
    void addtoCart(CartDTO cartDTO);
     void removeFromCart(Long id);
    List<CartDTO> getCartItemsByUser(Long userId);
    Cart updateCartQuantity(Long Id, int quantity);
}
