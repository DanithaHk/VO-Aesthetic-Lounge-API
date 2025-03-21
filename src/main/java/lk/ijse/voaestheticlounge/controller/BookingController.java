package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.AppoimentDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.dto.ServiceDTO;
import lk.ijse.voaestheticlounge.dto.UserDTO;
import lk.ijse.voaestheticlounge.service.BookingService;
import lk.ijse.voaestheticlounge.service.ServicesService;
import lk.ijse.voaestheticlounge.service.impl.BookingServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.EmailServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.ServiceServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.UserServiceImpl;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    private final BookingService bookingService;
    @Autowired
    private final BookingServiceImpl bookingServiceImpl;
    @Autowired
    ServiceServiceImpl serviceServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    EmailServiceImpl emailService;

    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveBooking(@RequestBody @Valid AppoimentDTO bookingDTO) {
       UserDTO userDTO = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
        bookingDTO.setUserId(userDTO.getId());
        String userName = userDTO.getUsername();

        ServiceDTO serviceDTO = serviceServiceImpl.findByName(bookingDTO.getServiceName());
        bookingDTO.setServiceId(serviceDTO.getId());
        bookingDTO.setPrice(serviceDTO.getPrice());

        LocalTime time = bookingDTO.getAppointmentTime();
        System.out.println("time "+time);
        bookingServiceImpl.save(bookingDTO);
        String userEmail = bookingDTO.getUserEmail();
        LocalDate date = bookingDTO.getAppointmentDate();

        emailService.sendBookingConfirmationEmail(
                userEmail,
                "Your Appointment is Confirmed – V/O Medspa 🌿\n",
                "Hi " + userName + ",\n\n" +
                        "Your appointment has been confirmed successfully. Here are the details:\n\n" +
                        "📅 Appointment Date: " + date + "\n" +
                        "⏰ Appointment Time: " + time + "\n" +
                        "📍 Location: No.100, Nupe, Matara\n" +
                        "📞 Contact: 0412265762\n\n" +
                        "What to Expect:\n" +
                        "Our expert team is ready to provide you with a relaxing and professional experience. If you have any questions before your appointment, feel free to call us!\n\n" +
                        "V/O Medspa Team\n" +
                        "📍 No.100, Nupe, Matara\n" +
                        "📞 0412265762"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", null));
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity <ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", null));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateBooking(@PathVariable Long id, @RequestBody @Valid AppoimentDTO bookingDTO) {
        bookingServiceImpl.update(id,bookingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Updated Successfully", null));
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        List<AppoimentDTO> appoimentDTO = bookingServiceImpl.getAll();
        for (AppoimentDTO fruit : appoimentDTO) {
            System.out.println(fruit);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", bookingService.getAll()));
    }
}
