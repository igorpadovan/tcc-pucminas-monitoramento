package poc.pucminas.service.impl;

import poc.pucminas.service.AreaDeRiscoService;
import poc.pucminas.domain.AreaDeRisco;
import poc.pucminas.repository.AreaDeRiscoRepository;
import poc.pucminas.service.dto.AreaDeRiscoDTO;
import poc.pucminas.service.mapper.AreaDeRiscoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AreaDeRisco}.
 */
@Service
@Transactional
public class AreaDeRiscoServiceImpl implements AreaDeRiscoService {

    private final Logger log = LoggerFactory.getLogger(AreaDeRiscoServiceImpl.class);

    private final AreaDeRiscoRepository areaDeRiscoRepository;

    private final AreaDeRiscoMapper areaDeRiscoMapper;

    public AreaDeRiscoServiceImpl(AreaDeRiscoRepository areaDeRiscoRepository, AreaDeRiscoMapper areaDeRiscoMapper) {
        this.areaDeRiscoRepository = areaDeRiscoRepository;
        this.areaDeRiscoMapper = areaDeRiscoMapper;
    }

    @Override
    public AreaDeRiscoDTO save(AreaDeRiscoDTO areaDeRiscoDTO) {
        log.debug("Request to save AreaDeRisco : {}", areaDeRiscoDTO);
        AreaDeRisco areaDeRisco = areaDeRiscoMapper.toEntity(areaDeRiscoDTO);
        areaDeRisco = areaDeRiscoRepository.save(areaDeRisco);
        return areaDeRiscoMapper.toDto(areaDeRisco);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AreaDeRiscoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AreaDeRiscos");
        return areaDeRiscoRepository.findAll(pageable)
            .map(areaDeRiscoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AreaDeRiscoDTO> findOne(Long id) {
        log.debug("Request to get AreaDeRisco : {}", id);
        return areaDeRiscoRepository.findById(id)
            .map(areaDeRiscoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AreaDeRisco : {}", id);
        areaDeRiscoRepository.deleteById(id);
    }
}
