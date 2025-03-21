package lk.ijse.voaestheticlounge.repo;

import lk.ijse.voaestheticlounge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
