package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.AppoimentDTO;

import lk.ijse.voaestheticlounge.entity.Bookings;
import lk.ijse.voaestheticlounge.repo.BookingRepository;
import lk.ijse.voaestheticlounge.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void save(AppoimentDTO bookingDTO) {
        LocalTime time = bookingDTO.getAppointmentTime();
        System.out.println(time);
        bookingRepository.save(modelMapper.map(bookingDTO, Bookings.class));
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void update(Long id, AppoimentDTO bookingDTO) {
        Bookings existingBooking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found."));
        existingBooking.setAppointmentTime(bookingDTO.getAppointmentTime());
        existingBooking.setAppointmentDate(bookingDTO.getAppointmentDate());
        bookingRepository.save(existingBooking);
    }

    @Override
    public List<AppoimentDTO> getAll() {
        List<Bookings> bookingsList = bookingRepository.findAll();

        // Print all bookings to the console (sout)
        bookingsList.forEach(booking -> System.out.println(booking));

        // If you want to print specific fields, you can do it like this:
        bookingsList.forEach(booking -> {
            System.out.println("ID: " + booking.getId() +
                    ", Appointment Time: " + booking.getAppointmentTime() +
                    ", Appointment Date: " + booking.getAppointmentDate() +
                    ", Price: " + booking.getPrice());
        });

        // Map the list to AppointmentDTO
        List<AppoimentDTO> appointmentDTOs = modelMapper.map(
                bookingsList,
                new TypeToken<List<AppoimentDTO>>() {}.getType());

        return appointmentDTOs;

    }

    @Override
    public boolean isDateTaken(LocalDate appointmentDate) {
        List<Bookings> bookingsOnDate = bookingRepository.findByAppointmentDate(appointmentDate);
        List<AppoimentDTO> appointmentDTOs = bookingsOnDate.stream()
                .map(booking -> modelMapper.map(booking, AppoimentDTO.class))
                .collect(Collectors.toList());
        return !appointmentDTOs.isEmpty();
    }

    @Override
    public boolean isTimeTaken(LocalDate appointmentDate, LocalTime appointmentTime) {
        List<Bookings> bookings = bookingRepository.findByAppointmentDateAndAppointmentTime(appointmentDate, appointmentTime);

        // Convert List<Bookings> → List<AppoimentDTO> using ModelMapper
        List<AppoimentDTO> bookingsOnDateAndTime = bookings.stream()
                .map(booking -> modelMapper.map(booking, AppoimentDTO.class))
                .collect(Collectors.toList());

        // Check if the list is empty
        return !bookingsOnDateAndTime.isEmpty();
    }
    public List<AppoimentDTO> getBookingsByUserId(Long userId) {
        List<Bookings> bookings = bookingRepository.findAllByUserId(userId);
        return bookings.stream().map(booking -> modelMapper.map(booking, AppoimentDTO.class)).collect(Collectors.toList());
    }
}
