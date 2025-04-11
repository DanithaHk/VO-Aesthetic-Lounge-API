package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.AppoimentDTO;
import lk.ijse.voaestheticlounge.dto.UserDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    void save(AppoimentDTO bookingDTO);

    void delete(Long id);

    void update(Long id, AppoimentDTO bookingDTO);

    List<AppoimentDTO> getAll();
    boolean isDateTaken(LocalDate appointmentDate);
    boolean isTimeTaken(LocalDate appointmentDate, LocalTime appointmentTime);


    List<AppoimentDTO> getBookingsByUserId(Long userId);
}
