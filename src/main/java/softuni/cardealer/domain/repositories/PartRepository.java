package softuni.cardealer.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.cardealer.domain.enitities.Part;

public interface PartRepository extends JpaRepository<Part,Long> {
}
