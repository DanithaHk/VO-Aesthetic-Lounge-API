package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.OrderDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.service.OrderService;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid OrderDTO orderDTO){
        System.out.println(orderDTO.getId());
        orderService.save(orderDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Order Added SuccessFully!",null));
    }
}
