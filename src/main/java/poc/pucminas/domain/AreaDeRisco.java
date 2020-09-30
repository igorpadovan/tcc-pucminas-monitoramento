package poc.pucminas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AreaDeRisco.
 */
@Entity
@Table(name = "area_de_risco")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AreaDeRisco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "local")
    private String local;

    @Column(name = "tamanho")
    private String tamanho;

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

    public AreaDeRisco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public AreaDeRisco local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTamanho() {
        return tamanho;
    }

    public AreaDeRisco tamanho(String tamanho) {
        this.tamanho = tamanho;
        return this;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AreaDeRisco)) {
            return false;
        }
        return id != null && id.equals(((AreaDeRisco) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AreaDeRisco{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", local='" + getLocal() + "'" +
            ", tamanho='" + getTamanho() + "'" +
            "}";
    }
}
