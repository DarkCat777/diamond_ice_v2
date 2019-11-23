package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.EmpleadoDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Access;
import model.entity.Empleado;
import model.entity.Recurso;
import model.entity.Rol;

public class EmpleadoTableView extends TableViewEntity<Empleado> {
    public EmpleadoTableView(TableView<Empleado> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Empleado, Long> idDniEmpleado = new TableColumn<>("DNI");
        idDniEmpleado.setCellValueFactory(new PropertyValueFactory<>("idDniEmpleado"));
        TableColumn<Empleado, String> nombreEmpleado = new TableColumn<>("NOMBRE");
        nombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        TableColumn<Empleado, String> apellidoEmpleado = new TableColumn<>("APELLIDO");
        apellidoEmpleado.setCellValueFactory(new PropertyValueFactory<>("apellidoEmpleado"));
        TableColumn<Empleado, String> correoEmpleado = new TableColumn<>("CORREO");
        correoEmpleado.setCellValueFactory(new PropertyValueFactory<>("correoEmpleado"));
        TableColumn<Empleado, Rol> idRol = new TableColumn<>("ROL");
        idRol.setCellValueFactory(new PropertyValueFactory<>("RolByIdRol"));
        tableViewEntity.getColumns().setAll(idDniEmpleado, nombreEmpleado, apellidoEmpleado, correoEmpleado, idRol);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        this.tableViewEntity.setItems(FXCollections.observableList(empleadoDAO.getAll()));
    }

}
