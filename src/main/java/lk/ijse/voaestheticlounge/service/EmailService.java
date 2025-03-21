package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.entity.Bookings;

public interface EmailService {
    void sendBookingConfirmationEmail(String toEmail, String subject, String body); // Method to send email for booking
}
