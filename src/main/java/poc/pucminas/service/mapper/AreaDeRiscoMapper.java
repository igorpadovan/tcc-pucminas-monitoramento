package poc.pucminas.service.mapper;


import poc.pucminas.domain.*;
import poc.pucminas.service.dto.AreaDeRiscoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AreaDeRisco} and its DTO {@link AreaDeRiscoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaDeRiscoMapper extends EntityMapper<AreaDeRiscoDTO, AreaDeRisco> {



    default AreaDeRisco fromId(Long id) {
        if (id == null) {
            return null;
        }
        AreaDeRisco areaDeRisco = new AreaDeRisco();
        areaDeRisco.setId(id);
        return areaDeRisco;
    }
}
