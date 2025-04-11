package lk.ijse.voaestheticlounge.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.*;
import lk.ijse.voaestheticlounge.service.BookingService;
import lk.ijse.voaestheticlounge.service.OrderService;
import lk.ijse.voaestheticlounge.service.ServicesService;
import lk.ijse.voaestheticlounge.service.impl.BookingServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.EmailServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.ServiceServiceImpl;
import lk.ijse.voaestheticlounge.service.impl.UserServiceImpl;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    OrderService orderService;
    @Autowired
    JwtUtil jwtUtil;

    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ResponseDTO> saveBooking(@RequestBody @Valid AppoimentDTO bookingDTO, @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        // Step 1: Fetch user details from email
        UserDTO userDTO = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Your email not register with us", null));
        }
        boolean isDateTaken = bookingService.isDateTaken(bookingDTO.getAppointmentDate());
        if (isDateTaken) {
            // Step 3: Check if the specific time slot is available on the given date
            boolean isTimeTaken = bookingService.isTimeTaken(bookingDTO.getAppointmentDate(), bookingDTO.getAppointmentTime());
            if (isTimeTaken) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(VarList.Bad_Request, "The appointment time slot is already taken", null));
            }
        }

        // Set user details in the booking
        bookingDTO.setUserId(userDTO.getId());
        String userName = userDTO.getUsername();

        // Step 2: Fetch service details by service name
        ServiceDTO serviceDTO = serviceServiceImpl.findByName(bookingDTO.getServiceName());
        if (serviceDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Service not found", null));
        }

        // Set service details in the booking
        bookingDTO.setServiceId(serviceDTO.getId());
        bookingDTO.setPrice(serviceDTO.getPrice());

        LocalTime time = bookingDTO.getAppointmentTime();
        System.out.println("Time of appointment: " + time);

        // Step 3: Save to order
        Long userId = bookingDTO.getUserId();
        LocalDate date = LocalDate.now();
        Double price = bookingDTO.getPrice();

        // Save order and get the last inserted order
        orderService.save(new OrderDTO(null, userId, date, price));
        OrderDTO lastOrder = orderService.getLastOrder();

        if (lastOrder != null) {
            bookingDTO.setOrderId(lastOrder.getId());

            // Step 4: Save booking details
            bookingServiceImpl.save(bookingDTO);

            // Send confirmation email
            String userEmail = bookingDTO.getUserEmail();
            LocalDate appointmentDate = bookingDTO.getAppointmentDate();
            Long bookingId = bookingDTO.getId();
            try {

                emailService.sendBookingConfirmationEmail(
                        userEmail,
                        "Your Appointment is Confirmed – V/O Medspa 🌿",
                        "<html>" +
                                "<body style='font-family: Arial, sans-serif;'>" +
                                "<h2 style='color: #28a745;'>Your Appointment is Confirmed! 🎉</h2>" +
                                "<p>Hi " + userName + ",</p>" +
                                "<p>Your appointment has been confirmed successfully. Here are the details:</p>" +
                                "<p><strong>📅 Appointment Date:</strong> " + appointmentDate + "<br>" +
                                "<strong>⏰ Appointment Time:</strong> " + time + "<br>" +
                                "<strong>📍 Location:</strong> No.100, Nupe, Matara<br>" +
                                "<strong>📞 Contact:</strong> 0412265762</p>" +


                                "<h3>💳 Secure Your Appointment with Payment</h3>" +
                                "<p>To confirm your appointment, please complete your payment.</p>" +
                                "<p style='text-align: center;'>" +
                                "<a href='http://localhost:8080/api/v1/booking/?appointmentId=" + bookingId + "' style='background-color: #f39c12; color: white; padding: 12px 20px; text-decoration: none; border-radius: 5px; font-size: 16px;'> Pay Now</a>" +
                                "</p>" +

                                "<h3>🌿 What to Expect</h3>" +
                                "<p>Our expert team is ready to provide you with a relaxing and professional experience. If you have any questions before your appointment, feel free to call us!</p>" +

                                "<p style='text-align: center;'>" +
                                "<a href='https://yourwebsite.com' style='background-color: #28a745; color: white; padding: 12px 20px; text-decoration: none; border-radius: 5px; font-size: 16px;'>🌿 Visit Our Website</a>" +
                                "</p>" +

                                "<p style='text-align: center;'>" +
                                "<a href='http://localhost:8080/api/v1/booking/delete/" + bookingId + "' style='background-color: #dc3545; color: white; padding: 12px 20px; text-decoration: none; border-radius: 5px; font-size: 16px;'> Cancel Appointment</a>" +
                                "</p>" +

                                "<p>Best Regards,<br><strong>V/O Medspa Team</strong><br>📍 No.100, Nupe, Matara<br>📞 0412265762</p>" +
                                "</body>" +
                                "</html>"

                );
            }catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("email not send ");
            }


            // Step 5: Return success response
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", null));
        } else {
            // Step 6: If there is an issue saving the order
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Bad_Request, "Failed to save the order", null));
        }
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ResponseDTO> getAllBookings(@RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        List<AppoimentDTO> appoimentDTO = bookingServiceImpl.getAll();
        for (AppoimentDTO fruit : appoimentDTO) {
            System.out.println(fruit);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", bookingService.getAll()));
    }
    @GetMapping("/get/{email}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ResponseDTO> getBookingByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        UserDTO user = userServiceImpl.findByEmail(email);
        List<AppoimentDTO> bookings = bookingService.getBookingsByUserId(user.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", bookings));
    }

}
