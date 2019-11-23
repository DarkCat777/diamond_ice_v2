package model.entity;

import model.id.AccessPK;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "t_access", schema = "public", catalog = "test")
@IdClass(AccessPK.class)
public class Access {
    @Id
    @NotNull(message = "Tiene que escoger un Rol")
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    @Id
    @NotNull(message = "Tiene que escoger un Recurso")
    @Column(name = "id_recurso", nullable = false)
    private Long idRecurso;
    @Basic
    @Column(name = "estado_registro", nullable = false)
    private Boolean estadoRegistro;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false, insertable = false, updatable = false)
    private Rol RolByIdRol;
    @ManyToOne
    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", nullable = false, insertable = false, updatable = false)
    private Recurso RecursoByIdRecurso;

    public Access(long idRol, long idRecurso, boolean estadoRegistro) {
        this.idRol = idRol;
        this.idRecurso = idRecurso;
        this.estadoRegistro = estadoRegistro;
    }

    public Access(Rol rolByIdRol, Recurso recursosByIdRecurso, boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
        this.idRecurso = recursosByIdRecurso.getIdRecurso();
        this.idRol = rolByIdRol.getIdRol();
    }

    public Access() {

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


    public Boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return idRol == access.idRol &&
                idRecurso == access.idRecurso &&
                estadoRegistro == access.estadoRegistro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idRecurso, estadoRegistro);
    }


    public Rol getRolByIdRol() {
        return RolByIdRol;
    }

    public void setRolByIdRol(Rol rolByIdRol) {
        RolByIdRol = rolByIdRol;
    }

    public Recurso getRecursoByIdRecurso() {
        return RecursoByIdRecurso;
    }

    public void setRecursoByIdRecurso(Recurso recursosByIdRecurso) {
        RecursoByIdRecurso = recursosByIdRecurso;
    }

    @Override
    public String toString() {
        return "Access -> " + RolByIdRol + "-" + RecursoByIdRecurso;
    }
}
