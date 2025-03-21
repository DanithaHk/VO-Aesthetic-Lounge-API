package lk.ijse.voaestheticlounge.repo;

import lk.ijse.voaestheticlounge.dto.CartDTO;
import lk.ijse.voaestheticlounge.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartReposittory extends JpaRepository<Cart,Long> {
    List<CartDTO> findByUserId(Long userId);
}
