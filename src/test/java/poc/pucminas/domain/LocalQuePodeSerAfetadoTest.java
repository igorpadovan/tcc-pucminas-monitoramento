package poc.pucminas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import poc.pucminas.web.rest.TestUtil;

public class LocalQuePodeSerAfetadoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalQuePodeSerAfetado.class);
        LocalQuePodeSerAfetado localQuePodeSerAfetado1 = new LocalQuePodeSerAfetado();
        localQuePodeSerAfetado1.setId(1L);
        LocalQuePodeSerAfetado localQuePodeSerAfetado2 = new LocalQuePodeSerAfetado();
        localQuePodeSerAfetado2.setId(localQuePodeSerAfetado1.getId());
        assertThat(localQuePodeSerAfetado1).isEqualTo(localQuePodeSerAfetado2);
        localQuePodeSerAfetado2.setId(2L);
        assertThat(localQuePodeSerAfetado1).isNotEqualTo(localQuePodeSerAfetado2);
        localQuePodeSerAfetado1.setId(null);
        assertThat(localQuePodeSerAfetado1).isNotEqualTo(localQuePodeSerAfetado2);
    }
}
