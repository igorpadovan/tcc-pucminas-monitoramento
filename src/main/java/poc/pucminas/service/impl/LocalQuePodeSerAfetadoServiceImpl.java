package poc.pucminas.service.impl;

import poc.pucminas.service.LocalQuePodeSerAfetadoService;
import poc.pucminas.domain.LocalQuePodeSerAfetado;
import poc.pucminas.repository.LocalQuePodeSerAfetadoRepository;
import poc.pucminas.service.dto.LocalQuePodeSerAfetadoDTO;
import poc.pucminas.service.mapper.LocalQuePodeSerAfetadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LocalQuePodeSerAfetado}.
 */
@Service
@Transactional
public class LocalQuePodeSerAfetadoServiceImpl implements LocalQuePodeSerAfetadoService {

    private final Logger log = LoggerFactory.getLogger(LocalQuePodeSerAfetadoServiceImpl.class);

    private final LocalQuePodeSerAfetadoRepository localQuePodeSerAfetadoRepository;

    private final LocalQuePodeSerAfetadoMapper localQuePodeSerAfetadoMapper;

    public LocalQuePodeSerAfetadoServiceImpl(LocalQuePodeSerAfetadoRepository localQuePodeSerAfetadoRepository, LocalQuePodeSerAfetadoMapper localQuePodeSerAfetadoMapper) {
        this.localQuePodeSerAfetadoRepository = localQuePodeSerAfetadoRepository;
        this.localQuePodeSerAfetadoMapper = localQuePodeSerAfetadoMapper;
    }

    @Override
    public LocalQuePodeSerAfetadoDTO save(LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO) {
        log.debug("Request to save LocalQuePodeSerAfetado : {}", localQuePodeSerAfetadoDTO);
        LocalQuePodeSerAfetado localQuePodeSerAfetado = localQuePodeSerAfetadoMapper.toEntity(localQuePodeSerAfetadoDTO);
        localQuePodeSerAfetado = localQuePodeSerAfetadoRepository.save(localQuePodeSerAfetado);
        return localQuePodeSerAfetadoMapper.toDto(localQuePodeSerAfetado);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LocalQuePodeSerAfetadoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LocalQuePodeSerAfetados");
        return localQuePodeSerAfetadoRepository.findAll(pageable)
            .map(localQuePodeSerAfetadoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LocalQuePodeSerAfetadoDTO> findOne(Long id) {
        log.debug("Request to get LocalQuePodeSerAfetado : {}", id);
        return localQuePodeSerAfetadoRepository.findById(id)
            .map(localQuePodeSerAfetadoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LocalQuePodeSerAfetado : {}", id);
        localQuePodeSerAfetadoRepository.deleteById(id);
    }
}
