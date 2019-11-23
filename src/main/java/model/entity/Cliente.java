package model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_cliente", schema = "public", catalog = "test")
public class Cliente {
    @Id
    @Min(value = 9999999, message = "No es un DNI valido.")
    @Max(value = 99999999, message = "No es un DNI valido.")
    @NotNull(message = "El dni no puede estar vacio.")
    @Column(name = "id_dni_cliente", nullable = false)
    private Long idDniCliente;
    @Basic
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Column(name = "nombre_cliente", nullable = false, length = 64)
    private String nombreCliente;
    @Basic
    @NotBlank(message = "El apellido no puede estar vacio.")
    @Column(name = "apellido_cliente", nullable = false, length = 64)
    private String apellidoCliente;
    @Basic
    @Max(value = 120, message = "Su edad no puede ser mayor a 120 años.")
    @Min(value = 18, message = "Tiene que ser mayor de edad.(18 años)")
    @Column(name = "edad_cliente", nullable = false)
    private int edadCliente;
    @Basic
    @Email(message = "No es un correo valido.")
    @Column(name = "correo_cliente", nullable = false, length = 128)
    private String correoCliente;
    @Basic
    @Max(value = 999999999, message = "No es un numero de celular valido.")
    @Min(value = 900000000, message = "No es un numero de celular valido.")
    @Column(name = "celular_cliente", nullable = false)
    private long celularCliente;
    @Basic
    @NotBlank(message = "Su direccion no puede estar vacia.")
    @Column(name = "direccion_cliente", nullable = false, length = 128)
    private String direccionCliente;
    @OneToMany(mappedBy = "ClienteByIdDniCliente", fetch = FetchType.LAZY)
    private List<OrdenServicioCab> OrdenServicioCabsByIdDniCliente;

    public Cliente(@Min(value = 9999999, message = "No es un DNI valido.") @Max(value = 99999999, message = "No es un DNI valido.") @NotNull(message = "El dni no puede estar vacio.") long idDniCliente, @NotBlank(message = "El nombre no puede estar vacio.") String nombreCliente, @NotBlank(message = "El apellido no puede estar vacio.") String apellidoCliente, @Max(value = 120, message = "Su edad no puede ser mayor a 120 años.") @Min(value = 18, message = "Tiene que ser mayor de edad.(18 años)") int edadCliente, @Email(message = "No es un correo valido.") String correoCliente, @Max(value = 999999999, message = "No es un numero de celular valido.") @Min(value = 900000000, message = "No es un numero de celular valido.") long celularCliente, @NotBlank(message = "Su direccion no puede estar vacia.") String direccionCliente) {
        this.idDniCliente = idDniCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.edadCliente = edadCliente;
        this.correoCliente = correoCliente;
        this.celularCliente = celularCliente;
        this.direccionCliente = direccionCliente;
    }

    public Cliente() {

    }

    public Long getIdDniCliente() {
        return idDniCliente;
    }

    public void setIdDniCliente(long idDniCliente) {
        this.idDniCliente = idDniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public int getEdadCliente() {
        return edadCliente;
    }

    public void setEdadCliente(int edadCliente) {
        this.edadCliente = edadCliente;
    }


    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public long getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(long celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idDniCliente == cliente.idDniCliente &&
                edadCliente == cliente.edadCliente &&
                celularCliente == cliente.celularCliente &&
                Objects.equals(nombreCliente, cliente.nombreCliente) &&
                Objects.equals(apellidoCliente, cliente.apellidoCliente) &&
                Objects.equals(correoCliente, cliente.correoCliente) &&
                Objects.equals(direccionCliente, cliente.direccionCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDniCliente, nombreCliente, apellidoCliente, edadCliente, correoCliente, celularCliente, direccionCliente);
    }

    public List<OrdenServicioCab> getOrdenServicioCabsByIdDniCliente() {
        return OrdenServicioCabsByIdDniCliente;
    }

    public void setOrdenServicioCabsByIdDniCliente(List<OrdenServicioCab> ordenServicioCabsByIdDniCliente) {
        OrdenServicioCabsByIdDniCliente = ordenServicioCabsByIdDniCliente;
    }

    @Override
    public String toString() {
        return nombreCliente + ", " + apellidoCliente;
    }
}
