package poc.pucminas.web.rest;

import poc.pucminas.service.LocalQuePodeSerAfetadoService;
import poc.pucminas.web.rest.errors.BadRequestAlertException;
import poc.pucminas.service.dto.LocalQuePodeSerAfetadoDTO;

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
 * REST controller for managing {@link poc.pucminas.domain.LocalQuePodeSerAfetado}.
 */
@RestController
@RequestMapping("/api")
public class LocalQuePodeSerAfetadoResource {

    private final Logger log = LoggerFactory.getLogger(LocalQuePodeSerAfetadoResource.class);

    private static final String ENTITY_NAME = "monitoramentoLocalQuePodeSerAfetado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocalQuePodeSerAfetadoService localQuePodeSerAfetadoService;

    public LocalQuePodeSerAfetadoResource(LocalQuePodeSerAfetadoService localQuePodeSerAfetadoService) {
        this.localQuePodeSerAfetadoService = localQuePodeSerAfetadoService;
    }

    /**
     * {@code POST  /local-que-pode-ser-afetados} : Create a new localQuePodeSerAfetado.
     *
     * @param localQuePodeSerAfetadoDTO the localQuePodeSerAfetadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localQuePodeSerAfetadoDTO, or with status {@code 400 (Bad Request)} if the localQuePodeSerAfetado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/local-que-pode-ser-afetados")
    public ResponseEntity<LocalQuePodeSerAfetadoDTO> createLocalQuePodeSerAfetado(@Valid @RequestBody LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO) throws URISyntaxException {
        log.debug("REST request to save LocalQuePodeSerAfetado : {}", localQuePodeSerAfetadoDTO);
        if (localQuePodeSerAfetadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new localQuePodeSerAfetado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocalQuePodeSerAfetadoDTO result = localQuePodeSerAfetadoService.save(localQuePodeSerAfetadoDTO);
        return ResponseEntity.created(new URI("/api/local-que-pode-ser-afetados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /local-que-pode-ser-afetados} : Updates an existing localQuePodeSerAfetado.
     *
     * @param localQuePodeSerAfetadoDTO the localQuePodeSerAfetadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localQuePodeSerAfetadoDTO,
     * or with status {@code 400 (Bad Request)} if the localQuePodeSerAfetadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localQuePodeSerAfetadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/local-que-pode-ser-afetados")
    public ResponseEntity<LocalQuePodeSerAfetadoDTO> updateLocalQuePodeSerAfetado(@Valid @RequestBody LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO) throws URISyntaxException {
        log.debug("REST request to update LocalQuePodeSerAfetado : {}", localQuePodeSerAfetadoDTO);
        if (localQuePodeSerAfetadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocalQuePodeSerAfetadoDTO result = localQuePodeSerAfetadoService.save(localQuePodeSerAfetadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, localQuePodeSerAfetadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /local-que-pode-ser-afetados} : get all the localQuePodeSerAfetados.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localQuePodeSerAfetados in body.
     */
    @GetMapping("/local-que-pode-ser-afetados")
    public ResponseEntity<List<LocalQuePodeSerAfetadoDTO>> getAllLocalQuePodeSerAfetados(Pageable pageable) {
        log.debug("REST request to get a page of LocalQuePodeSerAfetados");
        Page<LocalQuePodeSerAfetadoDTO> page = localQuePodeSerAfetadoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /local-que-pode-ser-afetados/:id} : get the "id" localQuePodeSerAfetado.
     *
     * @param id the id of the localQuePodeSerAfetadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localQuePodeSerAfetadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/local-que-pode-ser-afetados/{id}")
    public ResponseEntity<LocalQuePodeSerAfetadoDTO> getLocalQuePodeSerAfetado(@PathVariable Long id) {
        log.debug("REST request to get LocalQuePodeSerAfetado : {}", id);
        Optional<LocalQuePodeSerAfetadoDTO> localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localQuePodeSerAfetadoDTO);
    }

    /**
     * {@code DELETE  /local-que-pode-ser-afetados/:id} : delete the "id" localQuePodeSerAfetado.
     *
     * @param id the id of the localQuePodeSerAfetadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/local-que-pode-ser-afetados/{id}")
    public ResponseEntity<Void> deleteLocalQuePodeSerAfetado(@PathVariable Long id) {
        log.debug("REST request to delete LocalQuePodeSerAfetado : {}", id);
        localQuePodeSerAfetadoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
