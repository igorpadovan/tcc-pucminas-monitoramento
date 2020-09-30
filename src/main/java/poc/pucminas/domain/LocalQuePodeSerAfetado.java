package poc.pucminas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A LocalQuePodeSerAfetado.
 */
@Entity
@Table(name = "local_que_pode_ser_afetado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalQuePodeSerAfetado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JsonIgnoreProperties(value = "localQuePodeSerAfetados", allowSetters = true)
    private AreaDeRisco areaDeRisco;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public LocalQuePodeSerAfetado nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AreaDeRisco getAreaDeRisco() {
        return areaDeRisco;
    }

    public LocalQuePodeSerAfetado areaDeRisco(AreaDeRisco areaDeRisco) {
        this.areaDeRisco = areaDeRisco;
        return this;
    }

    public void setAreaDeRisco(AreaDeRisco areaDeRisco) {
        this.areaDeRisco = areaDeRisco;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalQuePodeSerAfetado)) {
            return false;
        }
        return id != null && id.equals(((LocalQuePodeSerAfetado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocalQuePodeSerAfetado{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
