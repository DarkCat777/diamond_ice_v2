package view.access;

import controller.dao.AccessDAO;
import controller.dao.DataAccessObjectException;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Access;
import model.entity.Empleado;
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

public class AccessIndex extends AbstractIndexView<Access> {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_add_access;

    @FXML
    private Button btn_edit_acceso;

    @FXML
    private Button btn_delete_access;

    @FXML
    private Button btn_view_acceso;

    @FXML
    private TableView<Access> tabla_accesos;

    @FXML
    void add_acceso(ActionEvent event) {
        try {
            AccessAdd accessAdd = new AccessAdd(thisStage);
            accessAdd.showAndWait();
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
    void deleteAccess(ActionEvent event) {
        if (tableViewEntity.getValueSelected() != null) {
            try {
                AccessDAO accessDAO = new AccessDAO();
                accessDAO.delete(tableViewEntity.getValueSelected());
                this.tableViewEntity.generateTableEntity();
            } catch (HibernateUtilException e) {
                BuilderAlert.buildAlert("No se encontro el recurso.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (DataAccessObjectException e) {
                BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (Exception e) {
                BuilderAlert.buildAlertException(e).show();
            }
        }
    }

    @FXML
    void editAccess(ActionEvent event) {

    }

    @FXML
    void viewAccess(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_accesos.fxml'.";
        assert btn_add_access != null : "fx:id=\"btn_add_access\" was not injected: check your FXML file 'li_accesos.fxml'.";
        assert btn_edit_acceso != null : "fx:id=\"btn_edit_acceso\" was not injected: check your FXML file 'li_accesos.fxml'.";
        assert btn_delete_access != null : "fx:id=\"btn_delete_access\" was not injected: check your FXML file 'li_accesos.fxml'.";
        assert btn_view_acceso != null : "fx:id=\"btn_view_acceso\" was not injected: check your FXML file 'li_accesos.fxml'.";
        assert tabla_accesos != null : "fx:id=\"tabla_accesos\" was not injected: check your FXML file 'li_accesos.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Access.class.getSimpleName(), tabla_accesos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    private Empleado empleado;

    public AccessIndex(Stage root, Empleado empleado) {
        super(root);
        this.empleado = empleado;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/access/li_accesos.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista accessAdd no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

}