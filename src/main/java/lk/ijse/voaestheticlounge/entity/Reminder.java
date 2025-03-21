package lk.ijse.voaestheticlounge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Appoiments_id", nullable = false)
    private Bookings Appoiment;

    @Column(nullable = false)
    private LocalDateTime reminderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReminderStatus status = ReminderStatus.PENDING;
    enum ReminderStatus {
        PENDING,
        SENT
    }

    public Reminder() {
    }

    public Reminder(Long id, User user, Bookings appoiment, LocalDateTime reminderDate, ReminderStatus status) {
        this.id = id;
        this.user = user;
        Appoiment = appoiment;
        this.reminderDate = reminderDate;
        this.status = status;
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

    public Bookings getAppoiment() {
        return Appoiment;
    }

    public void setAppoiment(Bookings appoiment) {
        Appoiment = appoiment;
    }

    public LocalDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }
}
