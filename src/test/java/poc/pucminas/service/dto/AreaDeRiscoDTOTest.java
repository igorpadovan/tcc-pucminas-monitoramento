package poc.pucminas.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import poc.pucminas.web.rest.TestUtil;

public class AreaDeRiscoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AreaDeRiscoDTO.class);
        AreaDeRiscoDTO areaDeRiscoDTO1 = new AreaDeRiscoDTO();
        areaDeRiscoDTO1.setId(1L);
        AreaDeRiscoDTO areaDeRiscoDTO2 = new AreaDeRiscoDTO();
        assertThat(areaDeRiscoDTO1).isNotEqualTo(areaDeRiscoDTO2);
        areaDeRiscoDTO2.setId(areaDeRiscoDTO1.getId());
        assertThat(areaDeRiscoDTO1).isEqualTo(areaDeRiscoDTO2);
        areaDeRiscoDTO2.setId(2L);
        assertThat(areaDeRiscoDTO1).isNotEqualTo(areaDeRiscoDTO2);
        areaDeRiscoDTO1.setId(null);
        assertThat(areaDeRiscoDTO1).isNotEqualTo(areaDeRiscoDTO2);
    }
}
