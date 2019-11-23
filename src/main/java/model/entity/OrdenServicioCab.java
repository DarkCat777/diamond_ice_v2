package model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_orden_servicio_cab", schema = "public", catalog = "test")
public class OrdenServicioCab {
    @Id
    //@NotNull(message = "La id de orden de servicio no puede ser nula.")
    @GeneratedValue
    @Column(name = "id_orden_servicio_cab", nullable = false)
    private long idOrdenServicioCab;
    @Basic
    @NotNull(message = "La orden de servicio debe pertenecer a algun cliente.")
    @Column(name = "id_dni_cliente", nullable = true)
    private Long idDniCliente;
    @Basic
    @NotNull(message = "La orden de servicio debe ser emitida por un empleado.")
    @Column(name = "id_dni_empleado", nullable = true)
    private Long idDniEmpleado;
    @Basic
    @Min(value = 0, message = "El pago adelantado no puede ser menor a cero.")
    @Column(name = "pago_adelantado", nullable = false, precision = 0)
    private double pagoAdelantado;
    @Basic
    @Min(value = 0, message = "El pago total no puede ser menor a 0.")
    @Column(name = "pago_total", nullable = false, precision = 0)
    private double pagoTotal;
    @ManyToOne
    @JoinColumn(name = "id_dni_cliente", referencedColumnName = "id_dni_cliente", insertable = false, updatable = false)
    private Cliente ClienteByIdDniCliente;
    @ManyToOne
    @JoinColumn(name = "id_dni_empleado", referencedColumnName = "id_dni_empleado", insertable = false, updatable = false)
    private Empleado EmpleadoByIdDniEmpleado;
    @OneToMany(mappedBy = "OrdenServicioCabByIdOrdenServicioCab", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrdenServicioDet> OrdenServicioDetsByIdOrdenServicioCab;

    public OrdenServicioCab(@NotNull(message = "La orden de servicio debe pertenecer a algun cliente.") Long idDniCliente, @NotNull(message = "La orden de servicio debe ser emitida por un empleado.") Long idDniEmpleado, @Min(value = 0, message = "El pago adelantado no puede ser menor a cero.") double pagoAdelantado, @Min(value = 0, message = "El pago total no puede ser menor a 0.") double pagoTotal) {
        this.idDniCliente = idDniCliente;
        this.idDniEmpleado = idDniEmpleado;
        this.pagoAdelantado = pagoAdelantado;
        this.pagoTotal = pagoTotal;
    }

    public OrdenServicioCab(Cliente clienteByIdDniCliente, Empleado empleadoByIdDniEmpleado, @Min(value = 0, message = "El pago adelantado no puede ser menor a cero.") double pagoAdelantado, @Min(value = 0, message = "El pago total no puede ser menor a 0.") double pagoTotal) {
        this.pagoAdelantado = pagoAdelantado;
        this.pagoTotal = pagoTotal;
        this.idDniCliente = clienteByIdDniCliente.getIdDniCliente();
        this.idDniEmpleado = empleadoByIdDniEmpleado.getIdDniEmpleado();
    }

    public OrdenServicioCab() {
    }

    public long getIdOrdenServicioCab() {
        return idOrdenServicioCab;
    }

    public void setIdOrdenServicioCab(long idOrdenServicioCab) {
        this.idOrdenServicioCab = idOrdenServicioCab;
    }

    public Long getIdDniCliente() {
        return idDniCliente;
    }

    public void setIdDniCliente(Long idDniCliente) {
        this.idDniCliente = idDniCliente;
    }

    public Long getIdDniEmpleado() {
        return idDniEmpleado;
    }

    public void setIdDniEmpleado(Long idDniEmpleado) {
        this.idDniEmpleado = idDniEmpleado;
    }

    public double getPagoAdelantado() {
        return pagoAdelantado;
    }

    public void setPagoAdelantado(double pagoAdelantado) {
        this.pagoAdelantado = pagoAdelantado;
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenServicioCab that = (OrdenServicioCab) o;
        return idOrdenServicioCab == that.idOrdenServicioCab &&
                Double.compare(that.pagoAdelantado, pagoAdelantado) == 0 &&
                Double.compare(that.pagoTotal, pagoTotal) == 0 &&
                Objects.equals(idDniCliente, that.idDniCliente) &&
                Objects.equals(idDniEmpleado, that.idDniEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenServicioCab, idDniCliente, idDniEmpleado, pagoAdelantado, pagoTotal);
    }

    public Cliente getClienteByIdDniCliente() {
        return ClienteByIdDniCliente;
    }

    public void setClienteByIdDniCliente(Cliente clienteByIdDniCliente) {
        ClienteByIdDniCliente = clienteByIdDniCliente;
    }

    public Empleado getEmpleadoByIdDniEmpleado() {
        return EmpleadoByIdDniEmpleado;
    }

    public void setEmpleadoByIdDniEmpleado(Empleado empleadoByIdDniEmpleado) {
        EmpleadoByIdDniEmpleado = empleadoByIdDniEmpleado;
    }

    public List<OrdenServicioDet> getOrdenServicioDetsByIdOrdenServicioCab() {
        return OrdenServicioDetsByIdOrdenServicioCab;
    }

    public void setOrdenServicioDetsByIdOrdenServicioCab(List<OrdenServicioDet> ordenServicioDetsByIdOrdenServicioCab) {
        OrdenServicioDetsByIdOrdenServicioCab = ordenServicioDetsByIdOrdenServicioCab;
    }

	@Override
	public String toString() {
		return "OrdenServicioCab [pagoAdelantado=" + pagoAdelantado + ", pagoTotal=" + pagoTotal
				+ ", ClienteByIdDniCliente=" + ClienteByIdDniCliente + ", EmpleadoByIdDniEmpleado="
				+ EmpleadoByIdDniEmpleado + ", OrdenServicioDetsByIdOrdenServicioCab="
				+ OrdenServicioDetsByIdOrdenServicioCab + "]";
	}
    
}
