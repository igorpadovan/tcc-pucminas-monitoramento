package poc.pucminas.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link poc.pucminas.domain.AreaDeRisco} entity.
 */
public class AreaDeRiscoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nome;

    private String local;

    private String tamanho;

    
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AreaDeRiscoDTO)) {
            return false;
        }

        return id != null && id.equals(((AreaDeRiscoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AreaDeRiscoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", local='" + getLocal() + "'" +
            ", tamanho='" + getTamanho() + "'" +
            "}";
    }
}
