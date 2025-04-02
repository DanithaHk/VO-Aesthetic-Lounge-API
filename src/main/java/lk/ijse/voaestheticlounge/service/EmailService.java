package lk.ijse.voaestheticlounge.service;

import jakarta.mail.MessagingException;
import lk.ijse.voaestheticlounge.entity.Bookings;

public interface EmailService {
    void sendBookingConfirmationEmail(String toEmail, String subject, String body) throws MessagingException; // Method to send email for booking
}
