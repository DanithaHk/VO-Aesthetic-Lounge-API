package lk.ijse.voaestheticlounge.dto;

import java.time.LocalTime;

public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private LocalTime appoimentDuration;

    public ServiceDTO() {
    }

    public ServiceDTO(Long id, String name, String description, double price, String imageUrl, LocalTime appoimentDuration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.appoimentDuration = appoimentDuration;
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
