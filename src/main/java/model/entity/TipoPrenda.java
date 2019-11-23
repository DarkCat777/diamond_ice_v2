package model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_tipo_prenda", schema = "public", catalog = "test")
public class TipoPrenda {
    @Id
    //@NotNull(message = "La id del tipo de prenda no puede ser nulo.")
    @GeneratedValue
    @Column(name = "id_tipo_prenda", nullable = false)
    private Long idTipoPrenda;
    @Basic
    @NotBlank(message = "El nombre del tipo de prenda no puede estar vacio.")
    @Column(name = "nombre_tipo_prenda", nullable = false, length = 64)
    private String nombreTipoPrenda;
    @Basic
    @NotBlank(message = "La descripción del tipo de prenda no puede estar vacia.")
    @Column(name = "descripcion_tipo_prenda", nullable = false, length = 255)
    private String descripcionTipoPrenda;
    @Basic
    @Column(name = "estado_registro", nullable = true)
    private Boolean estadoRegistro;
    @OneToMany(mappedBy = "TipoPrendaByIdTipoPrenda", fetch = FetchType.LAZY)
    private List<OrdenServicioDet> OrdenServicioDetsByIdTipoPrenda;

    public TipoPrenda(@NotBlank(message = "El nombre del tipo de prenda no puede estar vacio.") String nombreTipoPrenda, @NotBlank(message = "La descripción del tipo de prenda no puede estar vacia.") String descripcionTipoPrenda, Boolean estadoRegistro) {
        this.nombreTipoPrenda = nombreTipoPrenda;
        this.descripcionTipoPrenda = descripcionTipoPrenda;
        this.estadoRegistro = estadoRegistro;
    }

    public TipoPrenda() {
    }

    public long getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(long idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public String getNombreTipoPrenda() {
        return nombreTipoPrenda;
    }

    public void setNombreTipoPrenda(String nombreTipoPrenda) {
        this.nombreTipoPrenda = nombreTipoPrenda;
    }

    public String getDescripcionTipoPrenda() {
        return descripcionTipoPrenda;
    }

    public void setDescripcionTipoPrenda(String descripcionTipoPrenda) {
        this.descripcionTipoPrenda = descripcionTipoPrenda;
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
        TipoPrenda that = (TipoPrenda) o;
        return idTipoPrenda == that.idTipoPrenda &&
                Objects.equals(nombreTipoPrenda, that.nombreTipoPrenda) &&
                Objects.equals(descripcionTipoPrenda, that.descripcionTipoPrenda) &&
                Objects.equals(estadoRegistro, that.estadoRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPrenda, nombreTipoPrenda, descripcionTipoPrenda, estadoRegistro);
    }


    public List<OrdenServicioDet> getOrdenServicioDetsByIdTipoPrenda() {
        return OrdenServicioDetsByIdTipoPrenda;
    }

    public void setOrdenServicioDetsByIdTipoPrenda(List<OrdenServicioDet> ordenServicioDetsByIdTipoPrenda) {
        OrdenServicioDetsByIdTipoPrenda = ordenServicioDetsByIdTipoPrenda;
    }

    @Override
    public String toString() {
        return nombreTipoPrenda;
    }
}
