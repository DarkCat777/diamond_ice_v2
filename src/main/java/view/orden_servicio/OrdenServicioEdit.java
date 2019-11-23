package view.orden_servicio;

import javafx.collections.FXCollections;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.OrdenServicioCab;
import model.entity.OrdenServicioDet;

import java.util.List;

public class OrdenServicioEdit extends OrdenServicioAdd {

    protected List<OrdenServicioDet> ordenServicioDets;

    public OrdenServicioEdit(Stage root, OrdenServicioCab ordenServicioCab, Empleado empleado) {
        super(root, empleado);
        this.ordenServicioCab = ordenServicioCab;
        this.ordenServicioDets = ordenServicioCab.getOrdenServicioDetsByIdOrdenServicioCab();
        this.tabla_servicios.setItems(FXCollections.observableList(ordenServicioDets));
        this.dni_cliente.setText(ordenServicioCab.getClienteByIdDniCliente().getIdDniCliente() + "");
        this.nombre_apellido_cliente.setText(ordenServicioCab.getClienteByIdDniCliente().getNombreCliente() + ", " + ordenServicioCab.getClienteByIdDniCliente().getApellidoCliente());
        this.direccion_cliente.setText(ordenServicioCab.getClienteByIdDniCliente().getDireccionCliente());
        this.total_orden_pagar.setText(ordenServicioCab.getPagoTotal() + "");
        pagoTotal = ordenServicioCab.getPagoTotal();
        this.total_orden.setText(String.valueOf(ordenServicioCab.getPagoTotal() - ordenServicioCab.getPagoAdelantado()));
        this.pago_adelantado.setText(ordenServicioCab.getPagoAdelantado() + "");
    }
}
