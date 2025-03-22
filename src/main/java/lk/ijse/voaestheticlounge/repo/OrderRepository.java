package lk.ijse.voaestheticlounge.repo;

import lk.ijse.voaestheticlounge.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findFirstByOrderByIdDesc();
}
