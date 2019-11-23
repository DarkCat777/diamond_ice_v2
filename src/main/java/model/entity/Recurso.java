package model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_recurso", schema = "public", catalog = "test")
public class Recurso {
    @Id
    //@NotNull(message = "La id de recurso no puede ser nula.")
    @GeneratedValue
    @Column(name = "id_recurso", nullable = false)
    private Long idRecurso;
    @Basic
    @NotBlank(message = "El nombre del recurso no puede estar vacio.")
    @Column(name = "nombre_recurso", nullable = false, length = 64)
    private String nombreRecurso;
    @Basic
    @Column(name = "estado_registro", nullable = false)
    private boolean estadoRegistro;
    @OneToMany(mappedBy = "RecursoByIdRecurso", fetch = FetchType.LAZY)
    private List<Access> AccessesByIdRecurso;

    public Recurso(@NotBlank(message = "El nombre del recurso no puede estar vacio.") String nombreRecurso, boolean estadoRegistro) {
        this.nombreRecurso = nombreRecurso;
        this.estadoRegistro = estadoRegistro;
    }

    public Recurso() {

    }

    public long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }


    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recursos = (Recurso) o;
        return idRecurso == recursos.idRecurso &&
                estadoRegistro == recursos.estadoRegistro &&
                Objects.equals(nombreRecurso, recursos.nombreRecurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecurso, nombreRecurso, estadoRegistro);
    }

    public List<Access> getAccessesByIdRecurso() {
        return AccessesByIdRecurso;
    }

    public void setAccessesByIdRecurso(List<Access> accessesByIdRecurso) {
        AccessesByIdRecurso = accessesByIdRecurso;
    }

    @Override
    public String toString() {
        return nombreRecurso;
    }
}
