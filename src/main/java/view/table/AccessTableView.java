package view.table;

import controller.dao.AccessDAO;
import controller.dao.DataAccessObjectException;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Access;
import model.entity.Recurso;
import model.entity.Rol;

public class AccessTableView extends TableViewEntity<Access> {
    public AccessTableView(TableView<Access> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Access, Rol> idRol = new TableColumn<>("ROL");
        idRol.setCellValueFactory(new PropertyValueFactory<>("RolByIdRol"));
        TableColumn<Access, Recurso> idRecurso = new TableColumn<>("RECURSO");
        idRecurso.setCellValueFactory(new PropertyValueFactory<>("RecursoByIdRecurso"));
        TableColumn<Access, Boolean> est_reg = new TableColumn<>("ESTADO DE REGISTRO");
        est_reg.setCellValueFactory(new PropertyValueFactory<>("estadoRegistro"));
        tableViewEntity.getColumns().setAll(idRol, idRecurso, est_reg);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        AccessDAO accessDAO = new AccessDAO();
        tableViewEntity.setItems(FXCollections.observableList(accessDAO.getAll()));
    }
}
