package view.servicio;

import controller.dao.DataAccessObjectException;
import controller.dao.ServicioDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Servicio;
import view.AbstractIndexView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import view.table.TableViewFactory;

public class ServicioIndex extends AbstractIndexView<Servicio> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_agregar_servicios;

    @FXML
    private Button editar_servicios;

    @FXML
    private Button btn_eliminar_servicios;

    @FXML
    private Button btn_ver_servicio;

    @FXML
    private TableView<Servicio> tabla_contenidos;

    @FXML
    void agregar_servicios(ActionEvent event) {
        try {
            ServicioAdd servicioAdd = new ServicioAdd(thisStage);
            servicioAdd.showAndWait();
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
    void editar(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                ServicioEdit servicioEdit = new ServicioEdit(thisStage, this.tableViewEntity.getValueSelected());
                servicioEdit.showAndWait();
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
    void eliminar_servicio(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                ServicioDAO servicioDAO = new ServicioDAO();
                servicioDAO.delete(this.tableViewEntity.getValueSelected());
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
    void ver_servicio(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_servicios.fxml'.";
        assert btn_agregar_servicios != null : "fx:id=\"btn_agregar_servicios\" was not injected: check your FXML file 'li_servicios.fxml'.";
        assert editar_servicios != null : "fx:id=\"editar_servicios\" was not injected: check your FXML file 'li_servicios.fxml'.";
        assert btn_eliminar_servicios != null : "fx:id=\"btn_eliminar_servicios\" was not injected: check your FXML file 'li_servicios.fxml'.";
        assert btn_ver_servicio != null : "fx:id=\"btn_ver_servicio\" was not injected: check your FXML file 'li_servicios.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_servicios.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Servicio.class.getSimpleName(), tabla_contenidos);
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

    public ServicioIndex(Stage root, Empleado empleado) {
        super(root);
        this.empleado = empleado;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/servicio/li_servicios.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Servicio");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
