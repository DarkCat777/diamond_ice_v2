package model.entity;

import model.id.OrdenServicioDetPK;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "t_orden_servicio_det", schema = "public", catalog = "test")
@IdClass(OrdenServicioDetPK.class)
public class OrdenServicioDet {
    @Id
    @NotNull(message = "Debe pertenecer a la orden de servicio principal.")
    @Column(name = "id_orden_servicio_cab", nullable = false)
    private Long idOrdenServicioCab;
    @Id
    @NotNull(message = "Debe haber un servicio solicitado por el cliente.")
    @Column(name = "id_servicio", nullable = false)
    private Long idServicio;
    @Id
    @NotNull(message = "La prenda debe pertener a un tipo de prenda.")
    @Column(name = "id_tipo_prenda", nullable = false)
    private Long idTipoPrenda;
    @Basic
    @Positive(message = "El peso de las prendas debe ser mayor a 0 Kg.")
    @Column(name = "peso_kg", nullable = false, precision = 0)
    private double pesoKg;
    @Basic
    @NotBlank(message = "Debe existir una descripcion de la prenda o las prendas que se estan dejando.")
    @Column(name = "descripcion_prendas", nullable = false, length = 255)
    private String descripcionPrendas;
    @Basic
    @Min(value = 0, message = "La cantidad de las prendas deben ser mayor a 0.")
    @Column(name = "cantidad_prenda", nullable = false)
    private int cantidadPrenda;
    @ManyToOne
    @JoinColumn(name = "id_orden_servicio_cab", referencedColumnName = "id_orden_servicio_cab", nullable = false, insertable = false, updatable = false)
    private OrdenServicioCab OrdenServicioCabByIdOrdenServicioCab;
    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio", nullable = false, insertable = false, updatable = false)
    private Servicio ServicioByIdServicio;
    @ManyToOne
    @JoinColumn(name = "id_tipo_prenda", referencedColumnName = "id_tipo_prenda", nullable = false, insertable = false, updatable = false)
    private TipoPrenda TipoPrendaByIdTipoPrenda;

    public OrdenServicioDet(@NotNull(message = "Debe pertenecer a la orden de servicio principal.") long idOrdenServicioCab, @NotNull(message = "Debe haber un servicio solicitado por el cliente.") long idServicio, @NotNull(message = "La prenda debe pertener a un tipo de prenda.") long idTipoPrenda, @Min(value = 0, message = "El peso de las prendas debe ser mayor a 0 Kg.") double pesoKg, @NotBlank(message = "Debe existir una descripcion de la prenda o las prendas que se estan dejando.") String descripcionPrendas, @Min(value = 0, message = "La cantidad de las prendas deben ser mayor a 0.") int cantidadPrenda) {
        this.idOrdenServicioCab = idOrdenServicioCab;
        this.idServicio = idServicio;
        this.idTipoPrenda = idTipoPrenda;
        this.pesoKg = pesoKg;
        this.descripcionPrendas = descripcionPrendas;
        this.cantidadPrenda = cantidadPrenda;
    }

    public OrdenServicioDet(@Min(value = 0, message = "El peso de las prendas debe ser mayor a 0 Kg.") double pesoKg, @NotBlank(message = "Debe existir una descripcion de la prenda o las prendas que se estan dejando.") String descripcionPrendas, @Min(value = 0, message = "La cantidad de las prendas deben ser mayor a 0.") int cantidadPrenda, OrdenServicioCab ordenServicioCabByIdOrdenServicioCab, Servicio servicioByIdServicio, TipoPrenda tipoPrendaByIdTipoPrenda) {
        this.pesoKg = pesoKg;
        this.descripcionPrendas = descripcionPrendas;
        this.cantidadPrenda = cantidadPrenda;
        this.idOrdenServicioCab = ordenServicioCabByIdOrdenServicioCab.getIdOrdenServicioCab();
        this.idServicio = servicioByIdServicio.getIdServicio();
        this.idTipoPrenda = tipoPrendaByIdTipoPrenda.getIdTipoPrenda();
    }

    public OrdenServicioDet() {
    }

    public long getIdOrdenServicioCab() {
        return idOrdenServicioCab;
    }

    public void setIdOrdenServicioCab(long idOrdenServicioCab) {
        this.idOrdenServicioCab = idOrdenServicioCab;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

    public long getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(long idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDescripcionPrendas() {
        return descripcionPrendas;
    }

    public void setDescripcionPrendas(String descripcionPrendas) {
        this.descripcionPrendas = descripcionPrendas;
    }

    public int getCantidadPrenda() {
        return cantidadPrenda;
    }

    public void setCantidadPrenda(int cantidadPrenda) {
        this.cantidadPrenda = cantidadPrenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenServicioDet that = (OrdenServicioDet) o;
        return idOrdenServicioCab == that.idOrdenServicioCab &&
                idServicio == that.idServicio &&
                idTipoPrenda == that.idTipoPrenda &&
                Double.compare(that.pesoKg, pesoKg) == 0 &&
                cantidadPrenda == that.cantidadPrenda &&
                Objects.equals(descripcionPrendas, that.descripcionPrendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenServicioCab, idServicio, idTipoPrenda, pesoKg, descripcionPrendas, cantidadPrenda);
    }

    public OrdenServicioCab getOrdenServicioCabByIdOrdenServicioCab() {
        return OrdenServicioCabByIdOrdenServicioCab;
    }

    public void setOrdenServicioCabByIdOrdenServicioCab(OrdenServicioCab ordenServicioCabByIdOrdenServicioCab) {
        OrdenServicioCabByIdOrdenServicioCab = ordenServicioCabByIdOrdenServicioCab;
    }

    public Servicio getServicioByIdServicio() {
        return ServicioByIdServicio;
    }

    public void setServicioByIdServicio(Servicio servicioByIdServicio) {
        ServicioByIdServicio = servicioByIdServicio;
    }

    public TipoPrenda getTipoPrendaByIdTipoPrenda() {
        return TipoPrendaByIdTipoPrenda;
    }

    public void setTipoPrendaByIdTipoPrenda(TipoPrenda tipoPrendaByIdTipoPrenda) {
        TipoPrendaByIdTipoPrenda = tipoPrendaByIdTipoPrenda;
    }

	@Override
	public String toString() {
		return "OrdenServicioDet [pesoKg=" + pesoKg + ", descripcionPrendas=" + descripcionPrendas + ", cantidadPrenda="
				+ cantidadPrenda + ", ServicioByIdServicio=" + ServicioByIdServicio + ", TipoPrendaByIdTipoPrenda="
				+ TipoPrendaByIdTipoPrenda + "]";
	}
    
    
}
