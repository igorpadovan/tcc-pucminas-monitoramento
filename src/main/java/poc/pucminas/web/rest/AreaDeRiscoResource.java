package poc.pucminas.web.rest;

import poc.pucminas.service.AreaDeRiscoService;
import poc.pucminas.web.rest.errors.BadRequestAlertException;
import poc.pucminas.service.dto.AreaDeRiscoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link poc.pucminas.domain.AreaDeRisco}.
 */
@RestController
@RequestMapping("/api")
public class AreaDeRiscoResource {

    private final Logger log = LoggerFactory.getLogger(AreaDeRiscoResource.class);

    private static final String ENTITY_NAME = "monitoramentoAreaDeRisco";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AreaDeRiscoService areaDeRiscoService;

    public AreaDeRiscoResource(AreaDeRiscoService areaDeRiscoService) {
        this.areaDeRiscoService = areaDeRiscoService;
    }

    /**
     * {@code POST  /area-de-riscos} : Create a new areaDeRisco.
     *
     * @param areaDeRiscoDTO the areaDeRiscoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new areaDeRiscoDTO, or with status {@code 400 (Bad Request)} if the areaDeRisco has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/area-de-riscos")
    public ResponseEntity<AreaDeRiscoDTO> createAreaDeRisco(@Valid @RequestBody AreaDeRiscoDTO areaDeRiscoDTO) throws URISyntaxException {
        log.debug("REST request to save AreaDeRisco : {}", areaDeRiscoDTO);
        if (areaDeRiscoDTO.getId() != null) {
            throw new BadRequestAlertException("A new areaDeRisco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AreaDeRiscoDTO result = areaDeRiscoService.save(areaDeRiscoDTO);
        return ResponseEntity.created(new URI("/api/area-de-riscos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /area-de-riscos} : Updates an existing areaDeRisco.
     *
     * @param areaDeRiscoDTO the areaDeRiscoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated areaDeRiscoDTO,
     * or with status {@code 400 (Bad Request)} if the areaDeRiscoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the areaDeRiscoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/area-de-riscos")
    public ResponseEntity<AreaDeRiscoDTO> updateAreaDeRisco(@Valid @RequestBody AreaDeRiscoDTO areaDeRiscoDTO) throws URISyntaxException {
        log.debug("REST request to update AreaDeRisco : {}", areaDeRiscoDTO);
        if (areaDeRiscoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AreaDeRiscoDTO result = areaDeRiscoService.save(areaDeRiscoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, areaDeRiscoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /area-de-riscos} : get all the areaDeRiscos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of areaDeRiscos in body.
     */
    @GetMapping("/area-de-riscos")
    public ResponseEntity<List<AreaDeRiscoDTO>> getAllAreaDeRiscos(Pageable pageable) {
        log.debug("REST request to get a page of AreaDeRiscos");
        Page<AreaDeRiscoDTO> page = areaDeRiscoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /area-de-riscos/:id} : get the "id" areaDeRisco.
     *
     * @param id the id of the areaDeRiscoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the areaDeRiscoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/area-de-riscos/{id}")
    public ResponseEntity<AreaDeRiscoDTO> getAreaDeRisco(@PathVariable Long id) {
        log.debug("REST request to get AreaDeRisco : {}", id);
        Optional<AreaDeRiscoDTO> areaDeRiscoDTO = areaDeRiscoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(areaDeRiscoDTO);
    }

    /**
     * {@code DELETE  /area-de-riscos/:id} : delete the "id" areaDeRisco.
     *
     * @param id the id of the areaDeRiscoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/area-de-riscos/{id}")
    public ResponseEntity<Void> deleteAreaDeRisco(@PathVariable Long id) {
        log.debug("REST request to delete AreaDeRisco : {}", id);
        areaDeRiscoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
