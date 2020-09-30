package poc.pucminas.repository;

import poc.pucminas.domain.LocalQuePodeSerAfetado;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LocalQuePodeSerAfetado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalQuePodeSerAfetadoRepository extends JpaRepository<LocalQuePodeSerAfetado, Long> {
}
