package view.orden_servicio;

import controller.dao.DataAccessObjectException;
import controller.dao.OrdenServicioCabDAO;
import controller.dao.OrdenServicioDetDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.OrdenServicioCab;
import model.entity.OrdenServicioDet;
import view.AbstractIndexView;
import view.code_qr.CodeQR;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.CodingErrorAction;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import view.table.TableViewFactory;

public class OrdenServicioIndex extends AbstractIndexView<OrdenServicioCab> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_orden_agregar;

    @FXML
    private Button btn_editar_orden;

    @FXML
    private Button btn_eliminar_orden;

    @FXML
    private Button btn_ver_orden;

    @FXML
    private TableView<OrdenServicioCab> tabla_contenidos;

    @FXML
    void agregar_orden(ActionEvent event) {
        try {
            OrdenServicioAdd ordenServicioAdd = new OrdenServicioAdd(thisStage, empleado);
            ordenServicioAdd.showAndWait();
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    @FXML
    void editar_orden(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                OrdenServicioEdit ordenServicioEdit = new OrdenServicioEdit(thisStage, this.tableViewEntity.getValueSelected(), empleado);
                ordenServicioEdit.showAndWait();
                this.tableViewEntity.generateTableEntity();
            } catch (HibernateUtilException e) {
                BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (DataAccessObjectException e) {
                BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (Exception e) {
                BuilderAlert.buildAlertException(e).show();
            }
        }
    }

    @FXML
    void eliminar_orden(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                OrdenServicioCabDAO ordenServicioCabDAO = new OrdenServicioCabDAO();
                Long idCab = this.tableViewEntity.getValueSelected().getIdOrdenServicioCab();
                ordenServicioCabDAO.delete(this.tableViewEntity.getValueSelected());
                this.tableViewEntity.generateTableEntity();
            } catch (HibernateUtilException e) {
                BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (DataAccessObjectException e) {
                BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (Exception e) {
                BuilderAlert.buildAlertException(e).show();
            }
        }
    }

    @FXML
    void ver_orden(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        assert btn_orden_agregar != null : "fx:id=\"btn_orden_agregar\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        assert btn_editar_orden != null : "fx:id=\"btn_editar_orden\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        assert btn_eliminar_orden != null : "fx:id=\"btn_eliminar_orden\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        assert btn_ver_orden != null : "fx:id=\"btn_ver_orden\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_pedidos.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(OrdenServicioCab.class.getSimpleName(), tabla_contenidos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    protected Empleado empleado;

    public OrdenServicioIndex(Stage root, Empleado empleado) {
        super(root);
        this.empleado = empleado;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orden_servicio/li_pedidos.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Orden Servicio");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
