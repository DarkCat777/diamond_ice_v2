package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.RecursoDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Empleado;
import model.entity.Recurso;
import model.entity.Rol;

public class RecursoTableView extends TableViewEntity<Recurso> {
    public RecursoTableView(TableView<Recurso> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Recurso, Long> idRecurso = new TableColumn<>("ID");
        idRecurso.setCellValueFactory(new PropertyValueFactory<>("idRecurso"));
        TableColumn<Recurso, String> nombreRecurso = new TableColumn<>("NOMBRE");
        nombreRecurso.setCellValueFactory(new PropertyValueFactory<>("nombreRecurso"));
        TableColumn<Recurso, String> estadoDeRegistro = new TableColumn<>("ESTADO DE REGISTRO");
        estadoDeRegistro.setCellValueFactory(new PropertyValueFactory<>("estadoRegistro"));
        tableViewEntity.getColumns().setAll(idRecurso, nombreRecurso, estadoDeRegistro);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        RecursoDAO recursoDAO = new RecursoDAO();
        tableViewEntity.setItems(FXCollections.observableList(recursoDAO.getAll()));
    }

}
