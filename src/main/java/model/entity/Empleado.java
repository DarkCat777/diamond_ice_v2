package model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_empleado", schema = "public", catalog = "test")
@NamedQueries(@NamedQuery(name = Empleado.LOGIN, query = "SELECT  empleado FROM Empleado empleado WHERE empleado.correoEmpleado = :correoEmpleado AND empleado.passwordEmpleado = :passwordEmpleado"))
public class Empleado {
    public static final String LOGIN = "login";
    @Id
    @Min(value = 9999999, message = "No es un DNI valido.")
    @Max(value = 99999999, message = "No es un DNI valido.")
    @NotNull(message = "El dni no puede estar vacio.")
    @Column(name = "id_dni_empleado", nullable = false)
    private Long idDniEmpleado;
    @Basic
    @NotNull(message = "Tiene que tener un Rol.")
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    @Basic
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Column(name = "nombre_empleado", nullable = false, length = 64)
    private String nombreEmpleado;
    @Basic
    @NotBlank(message = "El apellido no puede estar vacio.")
    @Column(name = "apellido_empleado", nullable = false, length = 64)
    private String apellidoEmpleado;
    @Basic
    @NotBlank(message = "Su direccion no puede estar vacia.")
    @Column(name = "direccion_empleado", nullable = false, length = 255)
    private String direccionEmpleado;
    @Basic
    @Email(message = "No es un correo valido.")
    @Column(name = "correo_empleado", nullable = false, length = 128)
    private String correoEmpleado;
    @Basic
    @NotBlank(message = "Su contraseña no puede estar vacia.")
    @Column(name = "password_empleado", nullable = false, length = 16)
    private String passwordEmpleado;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false, insertable = false, updatable = false)
    private Rol RolByIdRol;
    @OneToMany(mappedBy = "EmpleadoByIdDniEmpleado", fetch = FetchType.LAZY)
    private List<OrdenServicioCab> OrdenServicioCabsByIdDniEmpleado;

    public Empleado(Long idDniEmpleado, Long idRol, String nombreEmpleado, String apellidoEmpleado, String direccionEmpleado, String correoEmpleado, String passwordEmpleado) {
        this.idDniEmpleado = idDniEmpleado;
        this.idRol = idRol;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.passwordEmpleado = passwordEmpleado;
    }

    public Empleado(Long idDniEmpleado, String nombreEmpleado, String apellidoEmpleado, String direccionEmpleado, String correoEmpleado, String passwordEmpleado, Rol rolByIdRol) {
        this.idDniEmpleado = idDniEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.passwordEmpleado = passwordEmpleado;
        this.idRol = rolByIdRol.getIdRol();
    }

    public Empleado() {
    }

    public long getIdDniEmpleado() {
        return idDniEmpleado;
    }

    public void setIdDniEmpleado(long idDniEmpleado) {
        this.idDniEmpleado = idDniEmpleado;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }


    public String getPasswordEmpleado() {
        return passwordEmpleado;
    }

    public void setPasswordEmpleado(String passwordEmpleado) {
        this.passwordEmpleado = passwordEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return idDniEmpleado == empleado.idDniEmpleado &&
                idRol == empleado.idRol &&
                Objects.equals(nombreEmpleado, empleado.nombreEmpleado) &&
                Objects.equals(apellidoEmpleado, empleado.apellidoEmpleado) &&
                Objects.equals(direccionEmpleado, empleado.direccionEmpleado) &&
                Objects.equals(correoEmpleado, empleado.correoEmpleado) &&
                Objects.equals(passwordEmpleado, empleado.passwordEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDniEmpleado, idRol, nombreEmpleado, apellidoEmpleado, direccionEmpleado, correoEmpleado, passwordEmpleado);
    }

    public Rol getRolByIdRol() {
        return RolByIdRol;
    }

    public void setRolByIdRol(Rol rolByIdRol) {
        RolByIdRol = rolByIdRol;
    }

    public List<OrdenServicioCab> getOrdenServicioCabsByIdDniEmpleado() {
        return OrdenServicioCabsByIdDniEmpleado;
    }

    public void setOrdenServicioCabsByIdDniEmpleado(List<OrdenServicioCab> ordenServicioCabsByIdDniEmpleado) {
        OrdenServicioCabsByIdDniEmpleado = ordenServicioCabsByIdDniEmpleado;
    }

    @Override
    public String toString() {
        return nombreEmpleado + ", " + apellidoEmpleado;
    }
}
