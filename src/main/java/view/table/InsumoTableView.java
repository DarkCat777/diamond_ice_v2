package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.InsumoDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.*;

public class InsumoTableView extends TableViewEntity<Insumo> {
    public InsumoTableView(TableView<Insumo> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Insumo, Long> idInsumo = new TableColumn<>("ID");
        idInsumo.setCellValueFactory(new PropertyValueFactory<>("idInsumo"));
        TableColumn<Insumo, String> nombreInsumo = new TableColumn<>("NOMBRE");
        nombreInsumo.setCellValueFactory(new PropertyValueFactory<>("nombreInsumo"));
        TableColumn<Insumo, TipoInsumo> tipoInsumo = new TableColumn<>("TIPO INSUMO");
        tipoInsumo.setCellValueFactory(new PropertyValueFactory<>("TipoInsumoByIdTipoInsumo"));
        TableColumn<Insumo, String> marcaInsumo = new TableColumn<>("MARCA");
        marcaInsumo.setCellValueFactory(new PropertyValueFactory<>("marcaInsumo"));
        TableColumn<Insumo, String> stockInsumo = new TableColumn<>("STOCK");
        stockInsumo.setCellValueFactory(new PropertyValueFactory<>("stockInsumo"));
        tableViewEntity.getColumns().setAll(idInsumo, nombreInsumo, tipoInsumo, marcaInsumo, stockInsumo);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        InsumoDAO insumoDAO = new InsumoDAO();
        tableViewEntity.setItems(FXCollections.observableList(insumoDAO.getAll()));
    }
}
