package lk.ijse.voaestheticlounge.repo;


import lk.ijse.voaestheticlounge.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {
}
