package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.PaymentDTO;
import lk.ijse.voaestheticlounge.dto.ProductDTO;
import lk.ijse.voaestheticlounge.entity.Payment;

import java.util.List;

public interface PaymentService {
    void processPayment(PaymentDTO paymentDTO);
/*
    Payment updatePaymentStatus(Long paymentId, Payment.PaymentStatus status);
*/
    Payment getPaymentById(Long paymentId);
    List<Payment> getAllPayments();
}
