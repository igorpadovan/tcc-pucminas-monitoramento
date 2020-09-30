package poc.pucminas.service;

import poc.pucminas.service.dto.LocalQuePodeSerAfetadoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link poc.pucminas.domain.LocalQuePodeSerAfetado}.
 */
public interface LocalQuePodeSerAfetadoService {

    /**
     * Save a localQuePodeSerAfetado.
     *
     * @param localQuePodeSerAfetadoDTO the entity to save.
     * @return the persisted entity.
     */
    LocalQuePodeSerAfetadoDTO save(LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO);

    /**
     * Get all the localQuePodeSerAfetados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LocalQuePodeSerAfetadoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" localQuePodeSerAfetado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LocalQuePodeSerAfetadoDTO> findOne(Long id);

    /**
     * Delete the "id" localQuePodeSerAfetado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
