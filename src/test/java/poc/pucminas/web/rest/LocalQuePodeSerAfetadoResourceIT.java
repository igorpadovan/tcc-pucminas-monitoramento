package poc.pucminas.web.rest;

import poc.pucminas.MonitoramentoApp;
import poc.pucminas.domain.LocalQuePodeSerAfetado;
import poc.pucminas.repository.LocalQuePodeSerAfetadoRepository;
import poc.pucminas.service.LocalQuePodeSerAfetadoService;
import poc.pucminas.service.dto.LocalQuePodeSerAfetadoDTO;
import poc.pucminas.service.mapper.LocalQuePodeSerAfetadoMapper;

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
 * Integration tests for the {@link LocalQuePodeSerAfetadoResource} REST controller.
 */
@SpringBootTest(classes = MonitoramentoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LocalQuePodeSerAfetadoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private LocalQuePodeSerAfetadoRepository localQuePodeSerAfetadoRepository;

    @Autowired
    private LocalQuePodeSerAfetadoMapper localQuePodeSerAfetadoMapper;

    @Autowired
    private LocalQuePodeSerAfetadoService localQuePodeSerAfetadoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocalQuePodeSerAfetadoMockMvc;

    private LocalQuePodeSerAfetado localQuePodeSerAfetado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocalQuePodeSerAfetado createEntity(EntityManager em) {
        LocalQuePodeSerAfetado localQuePodeSerAfetado = new LocalQuePodeSerAfetado()
            .nome(DEFAULT_NOME);
        return localQuePodeSerAfetado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocalQuePodeSerAfetado createUpdatedEntity(EntityManager em) {
        LocalQuePodeSerAfetado localQuePodeSerAfetado = new LocalQuePodeSerAfetado()
            .nome(UPDATED_NOME);
        return localQuePodeSerAfetado;
    }

    @BeforeEach
    public void initTest() {
        localQuePodeSerAfetado = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocalQuePodeSerAfetado() throws Exception {
        int databaseSizeBeforeCreate = localQuePodeSerAfetadoRepository.findAll().size();
        // Create the LocalQuePodeSerAfetado
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoMapper.toDto(localQuePodeSerAfetado);
        restLocalQuePodeSerAfetadoMockMvc.perform(post("/api/local-que-pode-ser-afetados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localQuePodeSerAfetadoDTO)))
            .andExpect(status().isCreated());

        // Validate the LocalQuePodeSerAfetado in the database
        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeCreate + 1);
        LocalQuePodeSerAfetado testLocalQuePodeSerAfetado = localQuePodeSerAfetadoList.get(localQuePodeSerAfetadoList.size() - 1);
        assertThat(testLocalQuePodeSerAfetado.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createLocalQuePodeSerAfetadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = localQuePodeSerAfetadoRepository.findAll().size();

        // Create the LocalQuePodeSerAfetado with an existing ID
        localQuePodeSerAfetado.setId(1L);
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoMapper.toDto(localQuePodeSerAfetado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocalQuePodeSerAfetadoMockMvc.perform(post("/api/local-que-pode-ser-afetados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localQuePodeSerAfetadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocalQuePodeSerAfetado in the database
        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = localQuePodeSerAfetadoRepository.findAll().size();
        // set the field null
        localQuePodeSerAfetado.setNome(null);

        // Create the LocalQuePodeSerAfetado, which fails.
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoMapper.toDto(localQuePodeSerAfetado);


        restLocalQuePodeSerAfetadoMockMvc.perform(post("/api/local-que-pode-ser-afetados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localQuePodeSerAfetadoDTO)))
            .andExpect(status().isBadRequest());

        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocalQuePodeSerAfetados() throws Exception {
        // Initialize the database
        localQuePodeSerAfetadoRepository.saveAndFlush(localQuePodeSerAfetado);

        // Get all the localQuePodeSerAfetadoList
        restLocalQuePodeSerAfetadoMockMvc.perform(get("/api/local-que-pode-ser-afetados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(localQuePodeSerAfetado.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getLocalQuePodeSerAfetado() throws Exception {
        // Initialize the database
        localQuePodeSerAfetadoRepository.saveAndFlush(localQuePodeSerAfetado);

        // Get the localQuePodeSerAfetado
        restLocalQuePodeSerAfetadoMockMvc.perform(get("/api/local-que-pode-ser-afetados/{id}", localQuePodeSerAfetado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(localQuePodeSerAfetado.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }
    @Test
    @Transactional
    public void getNonExistingLocalQuePodeSerAfetado() throws Exception {
        // Get the localQuePodeSerAfetado
        restLocalQuePodeSerAfetadoMockMvc.perform(get("/api/local-que-pode-ser-afetados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocalQuePodeSerAfetado() throws Exception {
        // Initialize the database
        localQuePodeSerAfetadoRepository.saveAndFlush(localQuePodeSerAfetado);

        int databaseSizeBeforeUpdate = localQuePodeSerAfetadoRepository.findAll().size();

        // Update the localQuePodeSerAfetado
        LocalQuePodeSerAfetado updatedLocalQuePodeSerAfetado = localQuePodeSerAfetadoRepository.findById(localQuePodeSerAfetado.getId()).get();
        // Disconnect from session so that the updates on updatedLocalQuePodeSerAfetado are not directly saved in db
        em.detach(updatedLocalQuePodeSerAfetado);
        updatedLocalQuePodeSerAfetado
            .nome(UPDATED_NOME);
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoMapper.toDto(updatedLocalQuePodeSerAfetado);

        restLocalQuePodeSerAfetadoMockMvc.perform(put("/api/local-que-pode-ser-afetados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localQuePodeSerAfetadoDTO)))
            .andExpect(status().isOk());

        // Validate the LocalQuePodeSerAfetado in the database
        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeUpdate);
        LocalQuePodeSerAfetado testLocalQuePodeSerAfetado = localQuePodeSerAfetadoList.get(localQuePodeSerAfetadoList.size() - 1);
        assertThat(testLocalQuePodeSerAfetado.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingLocalQuePodeSerAfetado() throws Exception {
        int databaseSizeBeforeUpdate = localQuePodeSerAfetadoRepository.findAll().size();

        // Create the LocalQuePodeSerAfetado
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO = localQuePodeSerAfetadoMapper.toDto(localQuePodeSerAfetado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocalQuePodeSerAfetadoMockMvc.perform(put("/api/local-que-pode-ser-afetados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localQuePodeSerAfetadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocalQuePodeSerAfetado in the database
        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocalQuePodeSerAfetado() throws Exception {
        // Initialize the database
        localQuePodeSerAfetadoRepository.saveAndFlush(localQuePodeSerAfetado);

        int databaseSizeBeforeDelete = localQuePodeSerAfetadoRepository.findAll().size();

        // Delete the localQuePodeSerAfetado
        restLocalQuePodeSerAfetadoMockMvc.perform(delete("/api/local-que-pode-ser-afetados/{id}", localQuePodeSerAfetado.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LocalQuePodeSerAfetado> localQuePodeSerAfetadoList = localQuePodeSerAfetadoRepository.findAll();
        assertThat(localQuePodeSerAfetadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
