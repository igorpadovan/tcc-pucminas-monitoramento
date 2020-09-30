package poc.pucminas.service;

import poc.pucminas.service.dto.AreaDeRiscoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link poc.pucminas.domain.AreaDeRisco}.
 */
public interface AreaDeRiscoService {

    /**
     * Save a areaDeRisco.
     *
     * @param areaDeRiscoDTO the entity to save.
     * @return the persisted entity.
     */
    AreaDeRiscoDTO save(AreaDeRiscoDTO areaDeRiscoDTO);

    /**
     * Get all the areaDeRiscos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AreaDeRiscoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" areaDeRisco.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AreaDeRiscoDTO> findOne(Long id);

    /**
     * Delete the "id" areaDeRisco.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
