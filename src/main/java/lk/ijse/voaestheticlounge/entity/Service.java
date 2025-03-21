package lk.ijse.voaestheticlounge.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    private LocalTime appoimentDuration;
    private String imageUrl;

    public Service() {
    }

    public Service(Long id, String name, String description, double price, LocalTime appoimentDuration, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.appoimentDuration = appoimentDuration;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalTime getAppoimentDuration() {
        return appoimentDuration;
    }

    public void setAppoimentDuration(LocalTime appoimentDuration) {
        this.appoimentDuration = appoimentDuration;
    }
}
