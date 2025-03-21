package lk.ijse.voaestheticlounge.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User  user;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    private Double price;

    public Bookings() {
    }

    public Bookings(Long id, User user, Service service, LocalTime appointmentTime, LocalDate appointmentDate, Double price) {
        this.id = id;
        this.user = user;
        this.service = service;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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


}
