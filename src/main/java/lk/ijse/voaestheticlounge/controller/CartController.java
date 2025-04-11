package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.CartDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.dto.UserDTO;
import lk.ijse.voaestheticlounge.entity.Cart;
import lk.ijse.voaestheticlounge.service.CartService;
import lk.ijse.voaestheticlounge.service.UserService;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*")    // Only allow frontend at this URL

public class CartController {
    @Autowired
    private final CartService cartService;
    @Autowired
    JwtUtil jwtUtil;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody @Valid CartDTO cartDTO, @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        System.out.println("ghgh" + username);
        UserDTO userDTO = userService.findByEmail(username);
        cartDTO.setUserId(userDTO.getId());
        System.out.println(cartDTO.getUserId());
        cartService.addtoCart(cartDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Cart Added SuccessFully!", cartDTO));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> getCartItemsByUser(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        String email = jwtUtil.getUsernameFromToken(token.substring(7));
        UserDTO userDTO = userService.findByEmail(email);
        Long userId1 = userDTO.getId();
        List<CartDTO> cartItemsByUser = cartService.getCartItemsByUser(userId1);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Success", cartItemsByUser));
    }

    @DeleteMapping("/remove/{cartId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId, @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        cartService.removeFromCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body("Cart Item Removed Successfully!");
    }

    @PutMapping("/update/{cartId}/{quantity}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> updateCartQuantity(@PathVariable Long cartId, @PathVariable int quantity , @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        Cart updatedCart = cartService.updateCartQuantity(cartId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
}
