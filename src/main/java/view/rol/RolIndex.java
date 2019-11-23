package view.rol;

import controller.dao.DataAccessObjectException;
import controller.dao.RolDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Rol;
import view.AbstractIndexView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.table.TableViewFactory;

public class RolIndex extends AbstractIndexView<Rol> {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_rol_agregar;

    @FXML
    private Button btn_editar_rol;

    @FXML
    private Button btn_eliminar_Rol;

    @FXML
    private Button btn_ver_rol;

    @FXML
    private TableView<Rol> tabla_contenidos;


    @FXML
    void agregar_rol(ActionEvent event) {
        try {
            RolAdd rolAdd = new RolAdd(thisStage);
            rolAdd.showAndWait();
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
    void editar_rol(ActionEvent event) {
        if (tableViewEntity.getValueSelected() != null) {
            try {
                RolEdit rolEdit = new RolEdit(thisStage, tableViewEntity.getValueSelected());
                rolEdit.showAndWait();
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
    void eliminar_rol(ActionEvent event) {
        if (tableViewEntity.getValueSelected() != null) {
            try {
                RolDAO rolDAO = new RolDAO();
                rolDAO.delete(tableViewEntity.getValueSelected());
                tableViewEntity.generateTableEntity();
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
    void ver_rol(ActionEvent event) {
        /*NO HAY VISTA VIEW :::::::::::::::::((((((((((((((((*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_rol.fxml'.";
        assert btn_rol_agregar != null : "fx:id=\"btn_rol_agregar\" was not injected: check your FXML file 'li_rol.fxml'.";
        assert btn_editar_rol != null : "fx:id=\"btn_editar_rol\" was not injected: check your FXML file 'li_rol.fxml'.";
        assert btn_eliminar_Rol != null : "fx:id=\"btn_eliminar_Rol\" was not injected: check your FXML file 'li_rol.fxml'.";
        assert btn_ver_rol != null : "fx:id=\"btn_ver_rol\" was not injected: check your FXML file 'li_rol.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_rol.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Rol.class.getSimpleName(), tabla_contenidos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    public RolIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rol/li_rol.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Recurso");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    private Empleado empleado;

    public RolIndex(Stage thisStage, Empleado empleado) {
        this(thisStage);
        this.empleado = empleado;
    }
}

