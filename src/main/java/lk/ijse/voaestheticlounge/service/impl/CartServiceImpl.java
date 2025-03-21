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

import java.util.List;
@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
     CartReposittory cartReposittory;
    @Autowired
     ModelMapper modelMapper;
    @Override
    public void addtoCart(CartDTO cartDTO) {
        cartReposittory.save(modelMapper.map(cartDTO,Cart.class));
    }

    @Override
    public void removeFromCart(Long id) {
        cartReposittory.deleteById(id);
    }

    @Override
    public List<CartDTO> getCartItemsByUser(Long userId) {
        return modelMapper.map(cartReposittory.findByUserId(userId),new TypeToken<List<CartDTO>>() {}.getType());
    }

    @Override
    public Cart updateCartQuantity(Long id, int quantity) {
        Cart cart = cartReposittory.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        return cartReposittory.save(cart);
    }
}
