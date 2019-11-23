package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoPrendaDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.TipoInsumo;
import model.entity.TipoPrenda;

public class TipoPrendaTableView extends TableViewEntity {
    public TipoPrendaTableView(TableView<TipoPrenda> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<TipoPrenda, Long> idTipoPrenda = new TableColumn<>("ID");
        idTipoPrenda.setCellValueFactory(new PropertyValueFactory<>("idTipoPrenda"));
        TableColumn<TipoPrenda, String> nombreTipoPrenda = new TableColumn<>("NOMBRE");
        nombreTipoPrenda.setCellValueFactory(new PropertyValueFactory<>("nombreTipoPrenda"));
        TableColumn<TipoPrenda, Boolean> estadoRegistro = new TableColumn<>("ESTADO REGISTRO");
        estadoRegistro.setCellValueFactory(new PropertyValueFactory<>("estadoRegistro"));
        tableViewEntity.getColumns().setAll(idTipoPrenda, nombreTipoPrenda, estadoRegistro);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO();
        tableViewEntity.setItems(FXCollections.observableList(tipoPrendaDAO.getAll()));
    }
}
