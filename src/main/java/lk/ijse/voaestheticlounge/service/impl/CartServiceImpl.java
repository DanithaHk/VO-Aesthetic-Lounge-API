package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.CartDTO;
import lk.ijse.voaestheticlounge.entity.Cart;
import lk.ijse.voaestheticlounge.repo.CartReposittory;
import lk.ijse.voaestheticlounge.service.CartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
     CartReposittory cartReposittory;
    @Autowired
     ModelMapper modelMapper;
//    @Override
//    public void addtoCart(CartDTO cartDTO) {
//        cartReposittory.save(modelMapper.map(cartDTO,Cart.class));
//    }
@Override
public void addtoCart(CartDTO cartDTO) {
    if (cartDTO == null) {
        throw new IllegalArgumentException("CartDTO cannot be null");
    }

    try {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        cartReposittory.save(cart);
    } catch (Exception e) {
        throw new RuntimeException("Failed to add item to cart", e);
    }
}


    @Override
    public void removeFromCart(Long id) {
        cartReposittory.deleteById(id);
    }

    @Override
    public List<CartDTO> getCartItemsByUser(Long userId) {
        List<Cart> cartItems = cartReposittory.findByUserId(userId);

        if (cartItems == null || cartItems.isEmpty()) {
            return new ArrayList<>();
        }


        return modelMapper.map(cartItems, new TypeToken<List<CartDTO>>() {}.getType());

    }

    @Override
    public Cart updateCartQuantity(Long id, int quantity) {
        Cart cart = cartReposittory.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        return cartReposittory.save(cart);
    }
}
