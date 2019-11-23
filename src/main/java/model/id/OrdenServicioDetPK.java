package model.id;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class OrdenServicioDetPK implements Serializable {
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

    public OrdenServicioDetPK(@NotNull(message = "Debe pertenecer a la orden de servicio principal.") Long idOrdenServicioCab, @NotNull(message = "Debe haber un servicio solicitado por el cliente.") Long idServicio, @NotNull(message = "La prenda debe pertener a un tipo de prenda.") Long idTipoPrenda) {
        this.idOrdenServicioCab = idOrdenServicioCab;
        this.idServicio = idServicio;
        this.idTipoPrenda = idTipoPrenda;
    }

    public OrdenServicioDetPK() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenServicioDetPK that = (OrdenServicioDetPK) o;
        return idOrdenServicioCab == that.idOrdenServicioCab &&
                idServicio == that.idServicio &&
                idTipoPrenda == that.idTipoPrenda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenServicioCab, idServicio, idTipoPrenda);
    }
}
