package softuni.cardealer.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.cardealer.domain.enitities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
