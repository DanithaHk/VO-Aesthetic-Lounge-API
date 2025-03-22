package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.OrderDTO;
import lk.ijse.voaestheticlounge.dto.ProductDTO;

import java.util.List;

public interface OrderService {
    void save(OrderDTO orderDTO);
    void delete(Long id);
    List<OrderDTO> getallOrders();
    OrderDTO getLastOrder();
}
