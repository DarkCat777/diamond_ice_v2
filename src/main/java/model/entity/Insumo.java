package model.entity;

import model.id.InsumoPK;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_insumo", schema = "public", catalog = "test")
@IdClass(InsumoPK.class)
public class Insumo implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_insumo", nullable = false)
    private Long idInsumo;
    @Id
    @NotNull(message = "El id de tipo insumo no puede ser nulo.")
    @Column(name = "id_tipo_insumo", nullable = true)
    private Long idTipoInsumo;
    @Basic
    @NotBlank(message = "El nombre del insumo no puede estar vacio.")
    @Column(name = "nombre_insumo", nullable = false, length = 64)
    private String nombreInsumo;
    @Basic
    @NotBlank(message = "La marca del insumo no puede estar vacia.")
    @Column(name = "marca_insumo", nullable = false, length = 64)
    private String marcaInsumo;
    @Basic
    @NotNull(message = "El precio del insumo no puede ser nulo.")
    @Positive(message = "El precio del insumo no puede ser menor a cero.")
    @Column(name = "precio_insumo", nullable = false, precision = 0)
    private double precioInsumo;
    @Basic
    @PositiveOrZero(message = "El stock del insumo no puede ser menor a cero.")
    @Column(name = "stock_insumo", nullable = false)
    private int stockInsumo;
    @Basic
    @Column(name = "estado_registro", nullable = true)
    private Boolean estadoRegistro;
    @ManyToOne
    @JoinColumn(name = "id_tipo_insumo", referencedColumnName = "id_tipo_insumo", nullable = false, insertable = false, updatable = false)
    private TipoInsumo TipoInsumoByIdTipoInsumo;
    @OneToMany(mappedBy = "Insumo")
    private List<Servicio> Servicios;

    public Insumo(@NotBlank(message = "El nombre del insumo no puede estar vacio.") String nombreInsumo, @NotBlank(message = "La marca del insumo no puede estar vacia.") String marcaInsumo, @NotNull(message = "El precio del insumo no puede ser nulo.") @Min(value = 0, message = "El precio del insumo no puede ser menor a cero.") double precioInsumo, @Min(value = 0, message = "El stock del insumo no puede ser menor a cero.") int stockInsumo, Boolean estadoRegistro) {
        this.nombreInsumo = nombreInsumo;
        this.marcaInsumo = marcaInsumo;
        this.precioInsumo = precioInsumo;
        this.stockInsumo = stockInsumo;
        this.estadoRegistro = estadoRegistro;
    }

    public Insumo(@NotBlank(message = "El nombre del insumo no puede estar vacio.") String nombreInsumo, @NotBlank(message = "La marca del insumo no puede estar vacia.") String marcaInsumo, @NotNull(message = "El precio del insumo no puede ser nulo.") @Min(value = 0, message = "El precio del insumo no puede ser menor a cero.") double precioInsumo, @Min(value = 0, message = "El stock del insumo no puede ser menor a cero.") int stockInsumo, Boolean estadoRegistro, TipoInsumo tipoInsumoByIdTipoInsumo) {
        this.nombreInsumo = nombreInsumo;
        this.marcaInsumo = marcaInsumo;
        this.precioInsumo = precioInsumo;
        this.stockInsumo = stockInsumo;
        this.estadoRegistro = estadoRegistro;
        this.idTipoInsumo = tipoInsumoByIdTipoInsumo.getIdTipoInsumo();
    }

    public Insumo() {

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


    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public String getMarcaInsumo() {
        return marcaInsumo;
    }

    public void setMarcaInsumo(String marcaInsumo) {
        this.marcaInsumo = marcaInsumo;
    }

    public double getPrecioInsumo() {
        return precioInsumo;
    }

    public void setPrecioInsumo(double precioInsumo) {
        this.precioInsumo = precioInsumo;
    }

    public int getStockInsumo() {
        return stockInsumo;
    }

    public void setStockInsumo(int stockInsumo) {
        this.stockInsumo = stockInsumo;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insumo insumo = (Insumo) o;
        return idInsumo == insumo.idInsumo &&
                idTipoInsumo == insumo.idTipoInsumo &&
                Double.compare(insumo.precioInsumo, precioInsumo) == 0 &&
                stockInsumo == insumo.stockInsumo &&
                Objects.equals(nombreInsumo, insumo.nombreInsumo) &&
                Objects.equals(marcaInsumo, insumo.marcaInsumo) &&
                Objects.equals(estadoRegistro, insumo.estadoRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInsumo, idTipoInsumo, nombreInsumo, marcaInsumo, precioInsumo, stockInsumo, estadoRegistro);
    }

    public TipoInsumo getTipoInsumoByIdTipoInsumo() {
        return TipoInsumoByIdTipoInsumo;
    }

    public void setTipoInsumoByIdTipoInsumo(TipoInsumo tipoInsumoByIdTipoInsumo) {
        TipoInsumoByIdTipoInsumo = tipoInsumoByIdTipoInsumo;
    }

    public List<Servicio> getServicios() {
        return Servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        Servicios = servicios;
    }

    @Override
    public String toString() {
        return nombreInsumo + " (" + marcaInsumo + ")";
    }
}
