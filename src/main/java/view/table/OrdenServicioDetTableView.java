package view.table;

import controller.dao.DataAccessObjectException;
import controller.dao.OrdenServicioDetDAO;
import controller.hibernate.HibernateUtilException;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.OrdenServicioCab;
import model.entity.OrdenServicioDet;
import model.entity.Servicio;
import model.entity.TipoPrenda;

public class OrdenServicioDetTableView extends TableViewEntity<OrdenServicioDet> {
    public OrdenServicioDetTableView(TableView<OrdenServicioDet> tableView) {
        super(tableView);
    }

    @Override
    public void generateTableEntity() throws HibernateUtilException, DataAccessObjectException {
        chargeColumn();
        chargeValues();
    }

    @Override
    public void chargeColumn() {
        TableColumn<OrdenServicioDet, Servicio> idServicio = new TableColumn<>("SERVICIO");
        idServicio.setCellValueFactory(new PropertyValueFactory<>("ServicioByIdServicio"));
        TableColumn<OrdenServicioDet, TipoPrenda> idTipoPrenda = new TableColumn<>("TIPO PRENDA");
        idTipoPrenda.setCellValueFactory(new PropertyValueFactory<>("TipoPrendaByIdTipoPrenda"));
        TableColumn<OrdenServicioDet, Double> pesoKg = new TableColumn<>("PESO / KG");
        pesoKg.setCellValueFactory(new PropertyValueFactory<>("pesoKg"));
        TableColumn<OrdenServicioDet, Integer> cantidadPrenda = new TableColumn<>("CANTIDAD DE PRENDAS");
        cantidadPrenda.setCellValueFactory(new PropertyValueFactory<>("cantidadPrenda"));
        tableViewEntity.getColumns().setAll(idServicio, idTipoPrenda, pesoKg, cantidadPrenda);
    }

    @Override
    public void chargeValues() throws DataAccessObjectException, HibernateUtilException {
        OrdenServicioDetDAO ordenServicioDetDAO = new OrdenServicioDetDAO();
        tableViewEntity.setItems(FXCollections.observableList(ordenServicioDetDAO.getAll()));
    }
}
