package model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_rol", schema = "public", catalog = "test")
public class Rol {
    @Id
    //@NotNull(message = "El id del rol no puede ser nulo.")
    @GeneratedValue
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    @Basic
    @NotBlank(message = "El nombre del rol no puede estar vacio.")
    @Column(name = "nombre_rol", nullable = false)
    private String nombreRol;
    @Basic
    @NotBlank(message = "La descripcion del rol no puede estar vacio.")
    @Column(name = "descripcion_rol", nullable = false)
    private String descripcionRol;
    @Basic
    @Column(name = "estado_registro", nullable = false)
    private Boolean estadoRegistro;
    @OneToMany(mappedBy = "RolByIdRol", fetch = FetchType.LAZY)
    private List<Access> AccessesByIdRol;
    @OneToMany(mappedBy = "RolByIdRol", fetch = FetchType.LAZY)
    private List<Empleado> EmpleadosByIdRol;

    public Rol(@NotBlank(message = "El nombre del rol no puede estar vacio.") String nombreRol, Boolean estadoRegistro) {
        this.nombreRol = nombreRol;
        this.estadoRegistro = estadoRegistro;
    }

    public Rol() {
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return idRol == rol.idRol &&
                nombreRol == rol.nombreRol &&
                estadoRegistro == rol.estadoRegistro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombreRol, estadoRegistro);
    }

    public List<Access> getAccessesByIdRol() {
        return AccessesByIdRol;
    }

    public void setAccessesByIdRol(List<Access> accessesByIdRol) {
        AccessesByIdRol = accessesByIdRol;
    }

    public List<Empleado> getEmpleadosByIdRol() {
        return EmpleadosByIdRol;
    }

    public void setEmpleadosByIdRol(List<Empleado> empleadosByIdRol) {
        EmpleadosByIdRol = empleadosByIdRol;
    }

    @Override
    public String toString() {
        return nombreRol;
    }
}
