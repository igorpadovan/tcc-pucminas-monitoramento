package poc.pucminas.service.mapper;


import poc.pucminas.domain.*;
import poc.pucminas.service.dto.LocalQuePodeSerAfetadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LocalQuePodeSerAfetado} and its DTO {@link LocalQuePodeSerAfetadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaDeRiscoMapper.class})
public interface LocalQuePodeSerAfetadoMapper extends EntityMapper<LocalQuePodeSerAfetadoDTO, LocalQuePodeSerAfetado> {

    @Mapping(source = "areaDeRisco.id", target = "areaDeRiscoId")
    @Mapping(source = "areaDeRisco.nome", target = "areaDeRiscoNome")
    LocalQuePodeSerAfetadoDTO toDto(LocalQuePodeSerAfetado localQuePodeSerAfetado);

    @Mapping(source = "areaDeRiscoId", target = "areaDeRisco")
    LocalQuePodeSerAfetado toEntity(LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO);

    default LocalQuePodeSerAfetado fromId(Long id) {
        if (id == null) {
            return null;
        }
        LocalQuePodeSerAfetado localQuePodeSerAfetado = new LocalQuePodeSerAfetado();
        localQuePodeSerAfetado.setId(id);
        return localQuePodeSerAfetado;
    }
}
