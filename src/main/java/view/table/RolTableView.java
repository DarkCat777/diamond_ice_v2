package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.RolDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Recurso;
import model.entity.Rol;

public class RolTableView extends TableViewEntity<Rol> {
    public RolTableView(TableView<Rol> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Rol, Long> idRol = new TableColumn<>("ID");
        idRol.setCellValueFactory(new PropertyValueFactory<>("idRol"));
        TableColumn<Rol, String> nombreRol = new TableColumn<>("NOMBRE");
        nombreRol.setCellValueFactory(new PropertyValueFactory<>("nombreRol"));
        TableColumn<Rol, String> estadoDeRegistro = new TableColumn<>("ESTADO DE REGISTRO");
        estadoDeRegistro.setCellValueFactory(new PropertyValueFactory<>("estadoRegistro"));
        tableViewEntity.getColumns().setAll(idRol, nombreRol, estadoDeRegistro);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        RolDAO rolDAO = new RolDAO();
        tableViewEntity.setItems(FXCollections.observableList(rolDAO.getAll()));
    }

}
