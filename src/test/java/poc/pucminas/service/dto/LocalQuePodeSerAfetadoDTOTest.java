package poc.pucminas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import poc.pucminas.web.rest.TestUtil;

public class LocalQuePodeSerAfetadoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalQuePodeSerAfetadoDTO.class);
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO1 = new LocalQuePodeSerAfetadoDTO();
        localQuePodeSerAfetadoDTO1.setId(1L);
        LocalQuePodeSerAfetadoDTO localQuePodeSerAfetadoDTO2 = new LocalQuePodeSerAfetadoDTO();
        assertThat(localQuePodeSerAfetadoDTO1).isNotEqualTo(localQuePodeSerAfetadoDTO2);
        localQuePodeSerAfetadoDTO2.setId(localQuePodeSerAfetadoDTO1.getId());
        assertThat(localQuePodeSerAfetadoDTO1).isEqualTo(localQuePodeSerAfetadoDTO2);
        localQuePodeSerAfetadoDTO2.setId(2L);
        assertThat(localQuePodeSerAfetadoDTO1).isNotEqualTo(localQuePodeSerAfetadoDTO2);
        localQuePodeSerAfetadoDTO1.setId(null);
        assertThat(localQuePodeSerAfetadoDTO1).isNotEqualTo(localQuePodeSerAfetadoDTO2);
    }
}
