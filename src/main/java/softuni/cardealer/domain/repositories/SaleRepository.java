package softuni.cardealer.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.cardealer.domain.enitities.Sale;

public interface SaleRepository extends  JpaRepository<Sale, Long> {
}
