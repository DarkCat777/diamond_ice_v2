package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.OrdenServicioCabDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.*;

public class OrdenServicioCabTableView extends TableViewEntity<OrdenServicioCab> {
    public OrdenServicioCabTableView(TableView<OrdenServicioCab> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<OrdenServicioCab, Long> idOrdenServicioCab = new TableColumn<>("ID");
        idOrdenServicioCab.setCellValueFactory(new PropertyValueFactory<>("idOrdenServicioCab"));
        TableColumn<OrdenServicioCab, Cliente> idCliente = new TableColumn<>("CLIENTE");
        idCliente.setCellValueFactory(new PropertyValueFactory<>("ClienteByIdDniCliente"));
        TableColumn<OrdenServicioCab, Empleado> idEmpleado = new TableColumn<>("EMPLEADO");
        idEmpleado.setCellValueFactory(new PropertyValueFactory<>("EmpleadoByIdDniEmpleado"));
        TableColumn<OrdenServicioCab, Double> pagoAdelantado = new TableColumn<>("SALDO");
        pagoAdelantado.setCellValueFactory(new PropertyValueFactory<>("pagoAdelantado"));
        TableColumn<OrdenServicioCab, Integer> pagoTotal = new TableColumn<>("PAGO TOTAL");
        pagoTotal.setCellValueFactory(new PropertyValueFactory<>("pagoTotal"));
        tableViewEntity.getColumns().setAll(idOrdenServicioCab, idEmpleado, idCliente, pagoAdelantado, pagoTotal);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        OrdenServicioCabDAO ordenServicioCabDAO = new OrdenServicioCabDAO();
        tableViewEntity.setItems(FXCollections.observableList(ordenServicioCabDAO.getAll()));
    }
}
