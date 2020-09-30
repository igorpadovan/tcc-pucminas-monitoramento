package poc.pucminas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AreaDeRiscoMapperTest {

    private AreaDeRiscoMapper areaDeRiscoMapper;

    @BeforeEach
    public void setUp() {
        areaDeRiscoMapper = new AreaDeRiscoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(areaDeRiscoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(areaDeRiscoMapper.fromId(null)).isNull();
    }
}
