package lk.ijse.voaestheticlounge.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.voaestheticlounge.entity.Payment;
import lk.ijse.voaestheticlounge.entity.Service;
import lk.ijse.voaestheticlounge.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppoimentDTO {
    private Long id;  // Booking ID
    private Long userId;  // ID of the user associated with the booking
    private Long serviceId;  // ID of the service associated with the booking
    private String serviceName;  // Name of the service associated with the booking
    private LocalTime appointmentTime;  // Appointment time
    private LocalDate appointmentDate;  // Appointment date
    private Double price;  // Price of the booking
    private Long orderId;  // ID of the associated order (if applicable)
    private String userEmail;;

    public AppoimentDTO(Long id, Long userId, Long serviceId, String serviceName, LocalTime appointmentTime, LocalDate appointmentDate, Double price, Long orderId, String userEmail) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.price = price;
        this.orderId = orderId;
        this.userEmail = userEmail;
    }

    public AppoimentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}