package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.ServicioDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Insumo;
import model.entity.Rol;
import model.entity.Servicio;
import model.entity.TipoInsumo;

public class ServicioTableView extends TableViewEntity<Servicio> {
    public ServicioTableView(TableView<Servicio> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<Servicio, Long> idServicio = new TableColumn<>("ID");
        idServicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
        TableColumn<Servicio, Insumo> insumo = new TableColumn<>("INSUMO");
        insumo.setCellValueFactory(new PropertyValueFactory<>("Insumo"));
        TableColumn<Servicio, String> nombreServicio = new TableColumn<>("NOMBRE");
        nombreServicio.setCellValueFactory(new PropertyValueFactory<>("nombreServicio"));
        TableColumn<Servicio, Double> precioServicio = new TableColumn<>("PRECIO");
        precioServicio.setCellValueFactory(new PropertyValueFactory<>("precioServicioXKg"));
        TableColumn<Servicio, Integer> cantidadInsumoReq = new TableColumn<>("CANTIDAD INSUMO REQ");
        cantidadInsumoReq.setCellValueFactory(new PropertyValueFactory<>("cantidadInsumo"));
        tableViewEntity.getColumns().setAll(idServicio, insumo, nombreServicio, precioServicio, cantidadInsumoReq);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        ServicioDAO servicioDAO = new ServicioDAO();
        tableViewEntity.setItems(FXCollections.observableList(servicioDAO.getAll()));
    }
}
