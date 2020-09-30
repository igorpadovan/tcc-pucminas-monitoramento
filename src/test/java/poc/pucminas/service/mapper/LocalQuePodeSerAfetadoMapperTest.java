package poc.pucminas.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalQuePodeSerAfetadoMapperTest {

    private LocalQuePodeSerAfetadoMapper localQuePodeSerAfetadoMapper;

    @BeforeEach
    public void setUp() {
        localQuePodeSerAfetadoMapper = new LocalQuePodeSerAfetadoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(localQuePodeSerAfetadoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(localQuePodeSerAfetadoMapper.fromId(null)).isNull();
    }
}
