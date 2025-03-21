package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.CartDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.entity.Cart;
import lk.ijse.voaestheticlounge.service.CartService;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1//cart")
public class CartController {
    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody @Valid CartDTO cartDTO) {
       cartService.addtoCart(cartDTO);
       return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Cart Added SuccessFully!",null));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getCartItemsByUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Success",cartService.getCartItemsByUser(userId)));
    }
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body("Cart Item Removed Successfully!");
    }
    @PutMapping("/update/{cartId}/{quantity}")
    public ResponseEntity<?> updateCartQuantity(@PathVariable Long cartId, @PathVariable int quantity) {
        Cart updatedCart = cartService.updateCartQuantity(cartId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
}
