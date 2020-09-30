package poc.pucminas.repository;

import poc.pucminas.domain.AreaDeRisco;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AreaDeRisco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AreaDeRiscoRepository extends JpaRepository<AreaDeRisco, Long> {
}
