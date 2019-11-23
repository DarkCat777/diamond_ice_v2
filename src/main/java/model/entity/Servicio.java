package model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_servicio", schema = "public", catalog = "test")
public class Servicio {
    @Id
    //@NotNull(message = "El id del servicio no puede ser Nulo.")
    @GeneratedValue
    @Column(name = "id_servicio", nullable = false)
    private Long idServicio;
    @Basic
    @NotNull(message = "El id de insumo  no puede ser nulo.")
    @Column(name = "id_insumo", nullable = false)
    private Long idInsumo;
    @Basic
    @NotNull(message = "El id de tipo insumo no puede ser nulo.")
    @Column(name = "id_tipo_insumo", nullable = false)
    private Long idTipoInsumo;
    @Basic
    @NotBlank(message = "El nombre del servicio no puede estar vacio.")
    @Column(name = "nombre_servicio", nullable = false, length = 64)
    private String nombreServicio;
    @Basic
    @NotBlank(message = "La descripción del servicio no puede estar vacia.")
    @Column(name = "descripcion_servicio", nullable = false, length = 255)
    private String descripcionServicio;
    @Basic
    @Positive(message = "El precio no pude ser menor a cero.")
    @Column(name = "precio_servicio_x_kg", nullable = false)
    private double precioServicioXKg;
    @Basic
    @PositiveOrZero(message = "La cantidad de insumo no puede ser menor a cero.")
    @Column(name = "cantidad_insumo", nullable = false)
    private int cantidadInsumo;
    @OneToMany(mappedBy = "ServicioByIdServicio", fetch = FetchType.LAZY)
    private List<OrdenServicioDet> OrdenServicioDetsByIdServicio;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "id_tipo_insumo", referencedColumnName = "id_tipo_insumo", nullable = false, insertable = false, updatable = false)})
    private Insumo Insumo;

    public Servicio(@NotNull(message = "El id de insumo  no puede ser nulo.") long idInsumo, @NotNull(message = "El id de tipo insumo no puede ser nulo.") long idTipoInsumo, @NotBlank(message = "El nombre del servicio no puede estar vacio.") String nombreServicio, @NotBlank(message = "La descripción del servicio no puede estar vacia.") String descripcionServicio, @Min(value = 0, message = "El precio no pude ser menor a cero.") int precioServicioXKg, @Min(value = 0, message = "La cantidad de insumo no puede ser menor a cero.") int cantidadInsumo) {
        this.idInsumo = idInsumo;
        this.idTipoInsumo = idTipoInsumo;
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.precioServicioXKg = precioServicioXKg;
        this.cantidadInsumo = cantidadInsumo;
    }

    public Servicio(@NotBlank(message = "El nombre del servicio no puede estar vacio.") String nombreServicio, @NotBlank(message = "La descripción del servicio no puede estar vacia.") String descripcionServicio, @Min(value = 0, message = "El precio no pude ser menor a cero.") int precioServicioXKg, @Min(value = 0, message = "La cantidad de insumo no puede ser menor a cero.") int cantidadInsumo, Insumo insumo) {
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.precioServicioXKg = precioServicioXKg;
        this.cantidadInsumo = cantidadInsumo;
        this.idInsumo = insumo.getIdInsumo();
        this.idTipoInsumo = insumo.getIdTipoInsumo();
    }

    public Servicio() {

    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

    public long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public long getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(long idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public double getPrecioServicioXKg() {
        return precioServicioXKg;
    }

    public void setPrecioServicioXKg(double precioServicioXKg) {
        this.precioServicioXKg = precioServicioXKg;
    }

    public int getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(int cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicio servicio = (Servicio) o;
        return idServicio == servicio.idServicio &&
                idInsumo == servicio.idInsumo &&
                idTipoInsumo == servicio.idTipoInsumo &&
                precioServicioXKg == servicio.precioServicioXKg &&
                cantidadInsumo == servicio.cantidadInsumo &&
                Objects.equals(nombreServicio, servicio.nombreServicio) &&
                Objects.equals(descripcionServicio, servicio.descripcionServicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServicio, idInsumo, idTipoInsumo, nombreServicio, descripcionServicio, precioServicioXKg, cantidadInsumo);
    }

    public Collection<OrdenServicioDet> getOrdenServicioDetsByIdServicio() {
        return OrdenServicioDetsByIdServicio;
    }

    public void setOrdenServicioDetsByIdServicio(List<OrdenServicioDet> ordenServicioDetsByIdServicio) {
        OrdenServicioDetsByIdServicio = ordenServicioDetsByIdServicio;
    }

    public Insumo getInsumo() {
        return Insumo;
    }

    public void setInsumo(Insumo insumo) {
        Insumo = insumo;
    }

    @Override
    public String toString() {
        return nombreServicio;
    }
}
