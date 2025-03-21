package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.AppoimentDTO;

import java.util.List;

public interface BookingService {
    void save(AppoimentDTO bookingDTO);

    void delete(Long id);

    void update(Long id, AppoimentDTO bookingDTO);

    List<AppoimentDTO> getAll();
}
