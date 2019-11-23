package view.empleado;

import controller.dao.DataAccessObjectException;
import controller.dao.EmpleadoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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

public class EmpleadoIndex extends AbstractIndexView<Empleado> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_empleados_agregar;

    @FXML
    private Button btn_editar_empleado;

    @FXML
    private Button btn_eliminar_empleado;

    @FXML
    private Button btn_ver_empleado;

    @FXML
    private TableView<Empleado> tabla_empleado;

    @FXML
    void agregar_empleado(ActionEvent event) {
        try {
            EmpleadoAdd empleadoAdd = new EmpleadoAdd(thisStage);
            empleadoAdd.showAndWait();
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
    void editar_empleado(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                EmpleadoEdit empleadoEdit = new EmpleadoEdit(thisStage, this.tableViewEntity.getValueSelected());
                empleadoEdit.showAndWait();
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
    void eliminar_empleado(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                empleadoDAO.delete(this.tableViewEntity.getValueSelected());
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
    void ver_empleado(ActionEvent event) {
        /*NO HAY VISTA CREADA*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_empleados.fxml'.";
        assert btn_empleados_agregar != null : "fx:id=\"btn_empleados_agregar\" was not injected: check your FXML file 'li_empleados.fxml'.";
        assert btn_editar_empleado != null : "fx:id=\"btn_editar_empleado\" was not injected: check your FXML file 'li_empleados.fxml'.";
        assert btn_eliminar_empleado != null : "fx:id=\"btn_eliminar_empleado\" was not injected: check your FXML file 'li_empleados.fxml'.";
        assert btn_ver_empleado != null : "fx:id=\"btn_ver_empleado\" was not injected: check your FXML file 'li_empleados.fxml'.";
        assert tabla_empleado != null : "fx:id=\"tabla_empleado\" was not injected: check your FXML file 'li_empleados.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Empleado.class.getSimpleName(), tabla_empleado);
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

    public EmpleadoIndex(Stage root, Empleado empleado) {
        this(root);
        this.empleado = empleado;
    }

    public EmpleadoIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/empleado/li_empleados.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Empleado");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
