package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.PaymentDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.entity.Payment;
import lk.ijse.voaestheticlounge.service.PaymentService;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @PostMapping("/process")
    public ResponseEntity<ResponseDTO> processPayment(@RequestBody @Valid PaymentDTO paymentDTO) {

        paymentService.processPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Payment Processed Successfully", null));
    }


    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
}
