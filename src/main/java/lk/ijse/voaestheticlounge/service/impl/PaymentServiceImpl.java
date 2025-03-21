package lk.ijse.voaestheticlounge.service.impl;


import lk.ijse.voaestheticlounge.dto.PaymentDTO;
import lk.ijse.voaestheticlounge.entity.Payment;
import lk.ijse.voaestheticlounge.repo.BookingRepository;
import lk.ijse.voaestheticlounge.repo.PaymentRespository;
import lk.ijse.voaestheticlounge.repo.ProductRepository;
import lk.ijse.voaestheticlounge.repo.UserRepository;
import lk.ijse.voaestheticlounge.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRespository paymentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public void processPayment(PaymentDTO paymentDTO) {
//        String transactionId = UUID.randomUUID().toString();
//
//        Payment payment = new Payment();
//        payment.setUserId(userRepository.findById(String.valueOf(paymentDTO.getUserId())).orElseThrow(() -> new RuntimeException("User not found")).getId());
//        payment.setTransactionId(transactionId);
//        payment.setAmount(paymentDTO.getAmount());
//        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
//        payment.setPaymentType(paymentDTO.getPaymentType());
//        payment.setBooking(paymentDTO.getBookingId() != null ? bookingRepository.findById(paymentDTO.getBookingId()).orElse(null) : null);
//
//        payment.setProductId(paymentDTO.getProductId() != null ? productRepository.findById(paymentDTO.getProductId()).orElse(null).getId() : null);
//        payment.setStatus(String.valueOf(Payment.PaymentStatus.PENDING)); // Enum Usage
//        payment.setCreatedAt(String.valueOf(LocalDateTime.now()));

        paymentRepository.save(modelMapper.map(paymentDTO, Payment.class));
    }

    /*@Override
    public Payment updatePaymentStatus(Long paymentId, Payment.PaymentStatus status) {
        *//*Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(String.valueOf(status)); // Enum usage
        return paymentRepository.save(payment);*//*
    }*/


    @Override
    public Payment getPaymentById(Long paymentId) {
        return modelMapper.map(paymentRepository.findById(paymentId).orElse(null), Payment.class);
    }

    @Override
    public List<Payment> getAllPayments() {
        return modelMapper.map(paymentRepository.findAll(), new TypeToken<List<Payment>>() {}.getType());
    }
}
