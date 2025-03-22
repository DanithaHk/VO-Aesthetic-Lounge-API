package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.OrderDTO;
import lk.ijse.voaestheticlounge.entity.Order;
import lk.ijse.voaestheticlounge.entity.Payment;
import lk.ijse.voaestheticlounge.repo.OrderRepository;
import lk.ijse.voaestheticlounge.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void save(OrderDTO orderDTO) {
       orderRepository.save(modelMapper.map(orderDTO, lk.ijse.voaestheticlounge.entity.Order.class));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> getallOrders() {
        return modelMapper.map(orderRepository.findAll(), new TypeToken<List<OrderDTO>>() {}.getType());
    }
    public OrderDTO getLastOrder() {
        Order lastOrder = orderRepository.findFirstByOrderByIdDesc();

        if (lastOrder == null) {
            return null; // No orders available
        }
        return modelMapper.map(lastOrder, OrderDTO.class);
    }
}
