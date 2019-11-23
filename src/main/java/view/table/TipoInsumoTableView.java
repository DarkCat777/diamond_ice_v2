package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoInsumoDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Insumo;
import model.entity.Servicio;
import model.entity.TipoInsumo;

public class TipoInsumoTableView extends TableViewEntity<TipoInsumo> {
    public TipoInsumoTableView(TableView<TipoInsumo> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<TipoInsumo, Long> idTipoInsumo = new TableColumn<>("ID");
        idTipoInsumo.setCellValueFactory(new PropertyValueFactory<>("idTipoInsumo"));
        TableColumn<TipoInsumo, String> nombreInsumo = new TableColumn<>("NOMBRE");
        nombreInsumo.setCellValueFactory(new PropertyValueFactory<>("nombreTipoInsumo"));
        TableColumn<TipoInsumo, Boolean> estadoRegistro = new TableColumn<>("ESTADO REGISTRO");
        estadoRegistro.setCellValueFactory(new PropertyValueFactory<>("estadoRegistro"));
        tableViewEntity.getColumns().setAll(idTipoInsumo, nombreInsumo, estadoRegistro);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        TipoInsumoDAO tipoInsumoDAO = new TipoInsumoDAO();
        tableViewEntity.setItems(FXCollections.observableList(tipoInsumoDAO.getAll()));
    }

}
