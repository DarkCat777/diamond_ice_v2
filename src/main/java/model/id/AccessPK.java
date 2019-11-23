package model.id;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class AccessPK implements Serializable {
    @Id
    @NotNull(message = "Tiene que escoger un Rol")
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    @Id
    @NotNull(message = "Tiene que escoger un Recurso")
    @Column(name = "id_recurso", nullable = false)
    private Long idRecurso;

    public AccessPK(@NotNull(message = "Tiene que escoger un Rol") Long idRol, @NotNull(message = "Tiene que escoger un Recurso") Long idRecurso) {
        this.idRol = idRol;
        this.idRecurso = idRecurso;
    }

    public AccessPK() {
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }


    public long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(long idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessPK accessPK = (AccessPK) o;
        return idRol == accessPK.idRol &&
                idRecurso == accessPK.idRecurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idRecurso);
    }
}
