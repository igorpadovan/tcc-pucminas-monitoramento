package poc.pucminas.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import poc.pucminas.web.rest.TestUtil;

public class AreaDeRiscoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AreaDeRisco.class);
        AreaDeRisco areaDeRisco1 = new AreaDeRisco();
        areaDeRisco1.setId(1L);
        AreaDeRisco areaDeRisco2 = new AreaDeRisco();
        areaDeRisco2.setId(areaDeRisco1.getId());
        assertThat(areaDeRisco1).isEqualTo(areaDeRisco2);
        areaDeRisco2.setId(2L);
        assertThat(areaDeRisco1).isNotEqualTo(areaDeRisco2);
        areaDeRisco1.setId(null);
        assertThat(areaDeRisco1).isNotEqualTo(areaDeRisco2);
    }
}
