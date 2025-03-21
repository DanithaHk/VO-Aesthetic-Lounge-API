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
    private Long id;
    private Long  userId;
    private String userEmail;
    private Long serviceId;
    private String serviceName;
    private LocalTime appointmentTime;
    private LocalDate appointmentDate;
    private Double price;

    public AppoimentDTO() {
    }

    public AppoimentDTO(Long id, Long userId, String userEmail, Long serviceId, String serviceName, LocalTime appointmentTime, LocalDate appointmentDate, Double price) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }



    public Long getUserId() {
        return userId;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

}