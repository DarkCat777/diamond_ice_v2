package view.table;

import controller.dao.ClienteDAO;
import controller.dao.DataAccessObjectException;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Access;
import model.entity.Cliente;
import model.entity.Recurso;
import model.entity.Rol;

public class ClienteTableView extends TableViewEntity<Cliente> {
    public ClienteTableView(TableView<Cliente> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Cliente, Long> idDniCliente = new TableColumn<>("DNI");
        idDniCliente.setCellValueFactory(new PropertyValueFactory<>("idDniCliente"));
        TableColumn<Cliente, String> nombreCliente = new TableColumn<>("NOMBRE");
        nombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        TableColumn<Cliente, String> apellidoCliente = new TableColumn<>("APELLIDO");
        apellidoCliente.setCellValueFactory(new PropertyValueFactory<>("apellidoCliente"));
        TableColumn<Cliente, String> correoCliente = new TableColumn<>("CORREO");
        correoCliente.setCellValueFactory(new PropertyValueFactory<>("correoCliente"));
        TableColumn<Cliente, Long> celularCliente = new TableColumn<>("CELULAR");
        celularCliente.setCellValueFactory(new PropertyValueFactory<>("celularCliente"));
        tableViewEntity.getColumns().setAll(idDniCliente, nombreCliente, apellidoCliente, correoCliente, celularCliente);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        ClienteDAO clienteDAO = new ClienteDAO();
        this.tableViewEntity.setItems(FXCollections.observableList(clienteDAO.getAll()));
    }

}
