package lk.ijse.voaestheticlounge.repo;

import lk.ijse.voaestheticlounge.entity.Service;
import lk.ijse.voaestheticlounge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Service findByName(String name);
}
