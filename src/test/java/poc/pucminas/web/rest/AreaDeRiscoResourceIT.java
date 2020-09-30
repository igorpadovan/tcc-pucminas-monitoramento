package poc.pucminas.web.rest;

import poc.pucminas.MonitoramentoApp;
import poc.pucminas.domain.AreaDeRisco;
import poc.pucminas.repository.AreaDeRiscoRepository;
import poc.pucminas.service.AreaDeRiscoService;
import poc.pucminas.service.dto.AreaDeRiscoDTO;
import poc.pucminas.service.mapper.AreaDeRiscoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AreaDeRiscoResource} REST controller.
 */
@SpringBootTest(classes = MonitoramentoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AreaDeRiscoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    private static final String DEFAULT_TAMANHO = "AAAAAAAAAA";
    private static final String UPDATED_TAMANHO = "BBBBBBBBBB";

    @Autowired
    private AreaDeRiscoRepository areaDeRiscoRepository;

    @Autowired
    private AreaDeRiscoMapper areaDeRiscoMapper;

    @Autowired
    private AreaDeRiscoService areaDeRiscoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAreaDeRiscoMockMvc;

    private AreaDeRisco areaDeRisco;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AreaDeRisco createEntity(EntityManager em) {
        AreaDeRisco areaDeRisco = new AreaDeRisco()
            .nome(DEFAULT_NOME)
            .local(DEFAULT_LOCAL)
            .tamanho(DEFAULT_TAMANHO);
        return areaDeRisco;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AreaDeRisco createUpdatedEntity(EntityManager em) {
        AreaDeRisco areaDeRisco = new AreaDeRisco()
            .nome(UPDATED_NOME)
            .local(UPDATED_LOCAL)
            .tamanho(UPDATED_TAMANHO);
        return areaDeRisco;
    }

    @BeforeEach
    public void initTest() {
        areaDeRisco = createEntity(em);
    }

    @Test
    @Transactional
    public void createAreaDeRisco() throws Exception {
        int databaseSizeBeforeCreate = areaDeRiscoRepository.findAll().size();
        // Create the AreaDeRisco
        AreaDeRiscoDTO areaDeRiscoDTO = areaDeRiscoMapper.toDto(areaDeRisco);
        restAreaDeRiscoMockMvc.perform(post("/api/area-de-riscos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(areaDeRiscoDTO)))
            .andExpect(status().isCreated());

        // Validate the AreaDeRisco in the database
        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeCreate + 1);
        AreaDeRisco testAreaDeRisco = areaDeRiscoList.get(areaDeRiscoList.size() - 1);
        assertThat(testAreaDeRisco.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAreaDeRisco.getLocal()).isEqualTo(DEFAULT_LOCAL);
        assertThat(testAreaDeRisco.getTamanho()).isEqualTo(DEFAULT_TAMANHO);
    }

    @Test
    @Transactional
    public void createAreaDeRiscoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = areaDeRiscoRepository.findAll().size();

        // Create the AreaDeRisco with an existing ID
        areaDeRisco.setId(1L);
        AreaDeRiscoDTO areaDeRiscoDTO = areaDeRiscoMapper.toDto(areaDeRisco);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAreaDeRiscoMockMvc.perform(post("/api/area-de-riscos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(areaDeRiscoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AreaDeRisco in the database
        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = areaDeRiscoRepository.findAll().size();
        // set the field null
        areaDeRisco.setNome(null);

        // Create the AreaDeRisco, which fails.
        AreaDeRiscoDTO areaDeRiscoDTO = areaDeRiscoMapper.toDto(areaDeRisco);


        restAreaDeRiscoMockMvc.perform(post("/api/area-de-riscos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(areaDeRiscoDTO)))
            .andExpect(status().isBadRequest());

        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAreaDeRiscos() throws Exception {
        // Initialize the database
        areaDeRiscoRepository.saveAndFlush(areaDeRisco);

        // Get all the areaDeRiscoList
        restAreaDeRiscoMockMvc.perform(get("/api/area-de-riscos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(areaDeRisco.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL)))
            .andExpect(jsonPath("$.[*].tamanho").value(hasItem(DEFAULT_TAMANHO)));
    }
    
    @Test
    @Transactional
    public void getAreaDeRisco() throws Exception {
        // Initialize the database
        areaDeRiscoRepository.saveAndFlush(areaDeRisco);

        // Get the areaDeRisco
        restAreaDeRiscoMockMvc.perform(get("/api/area-de-riscos/{id}", areaDeRisco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(areaDeRisco.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL))
            .andExpect(jsonPath("$.tamanho").value(DEFAULT_TAMANHO));
    }
    @Test
    @Transactional
    public void getNonExistingAreaDeRisco() throws Exception {
        // Get the areaDeRisco
        restAreaDeRiscoMockMvc.perform(get("/api/area-de-riscos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAreaDeRisco() throws Exception {
        // Initialize the database
        areaDeRiscoRepository.saveAndFlush(areaDeRisco);

        int databaseSizeBeforeUpdate = areaDeRiscoRepository.findAll().size();

        // Update the areaDeRisco
        AreaDeRisco updatedAreaDeRisco = areaDeRiscoRepository.findById(areaDeRisco.getId()).get();
        // Disconnect from session so that the updates on updatedAreaDeRisco are not directly saved in db
        em.detach(updatedAreaDeRisco);
        updatedAreaDeRisco
            .nome(UPDATED_NOME)
            .local(UPDATED_LOCAL)
            .tamanho(UPDATED_TAMANHO);
        AreaDeRiscoDTO areaDeRiscoDTO = areaDeRiscoMapper.toDto(updatedAreaDeRisco);

        restAreaDeRiscoMockMvc.perform(put("/api/area-de-riscos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(areaDeRiscoDTO)))
            .andExpect(status().isOk());

        // Validate the AreaDeRisco in the database
        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeUpdate);
        AreaDeRisco testAreaDeRisco = areaDeRiscoList.get(areaDeRiscoList.size() - 1);
        assertThat(testAreaDeRisco.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAreaDeRisco.getLocal()).isEqualTo(UPDATED_LOCAL);
        assertThat(testAreaDeRisco.getTamanho()).isEqualTo(UPDATED_TAMANHO);
    }

    @Test
    @Transactional
    public void updateNonExistingAreaDeRisco() throws Exception {
        int databaseSizeBeforeUpdate = areaDeRiscoRepository.findAll().size();

        // Create the AreaDeRisco
        AreaDeRiscoDTO areaDeRiscoDTO = areaDeRiscoMapper.toDto(areaDeRisco);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAreaDeRiscoMockMvc.perform(put("/api/area-de-riscos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(areaDeRiscoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AreaDeRisco in the database
        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAreaDeRisco() throws Exception {
        // Initialize the database
        areaDeRiscoRepository.saveAndFlush(areaDeRisco);

        int databaseSizeBeforeDelete = areaDeRiscoRepository.findAll().size();

        // Delete the areaDeRisco
        restAreaDeRiscoMockMvc.perform(delete("/api/area-de-riscos/{id}", areaDeRisco.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AreaDeRisco> areaDeRiscoList = areaDeRiscoRepository.findAll();
        assertThat(areaDeRiscoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
