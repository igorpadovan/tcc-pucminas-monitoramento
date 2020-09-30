package poc.pucminas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link poc.pucminas.domain.LocalQuePodeSerAfetado} entity.
 */
public class LocalQuePodeSerAfetadoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nome;


    private Long areaDeRiscoId;

    private String areaDeRiscoNome;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAreaDeRiscoId() {
        return areaDeRiscoId;
    }

    public void setAreaDeRiscoId(Long areaDeRiscoId) {
        this.areaDeRiscoId = areaDeRiscoId;
    }

    public String getAreaDeRiscoNome() {
        return areaDeRiscoNome;
    }

    public void setAreaDeRiscoNome(String areaDeRiscoNome) {
        this.areaDeRiscoNome = areaDeRiscoNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalQuePodeSerAfetadoDTO)) {
            return false;
        }

        return id != null && id.equals(((LocalQuePodeSerAfetadoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocalQuePodeSerAfetadoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", areaDeRiscoId=" + getAreaDeRiscoId() +
            ", areaDeRiscoNome='" + getAreaDeRiscoNome() + "'" +
            "}";
    }
}
