package view.empleado;

import controller.dao.DataAccessObjectException;
import controller.dao.EmpleadoDAO;
import controller.dao.RolDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Rol;
import view.AbstractView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EmpleadoAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected TextField nombre_empleado;

    @FXML
    protected TextField apellido_empleado;

    @FXML
    protected TextField direccion_empleado;

    @FXML
    protected TextField dni_empleado;

    @FXML
    protected PasswordField edad_empleado;

    @FXML
    protected TextField correo_empleado;

    @FXML
    private Button registrar_empleado;

    @FXML
    private Button cancelar_empleado;

    @FXML
    protected ComboBox<Rol> comb_rol;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar(ActionEvent event) {
        try {
            if (comb_rol.getValue() != null)
                empleado.setIdRol(comb_rol.getValue().getIdRol());
            empleado.setNombreEmpleado(nombre_empleado.getText());
            empleado.setCorreoEmpleado(correo_empleado.getText());
            empleado.setPasswordEmpleado(edad_empleado.getText());
            empleado.setDireccionEmpleado(direccion_empleado.getText());
            if (!dni_empleado.getText().equals(""))
                empleado.setIdDniEmpleado(Long.parseLong(dni_empleado.getText()));
            empleado.setApellidoEmpleado(apellido_empleado.getText());
            ValidatorEntity<Empleado> empleadoValidatorEntity = new ValidatorEntity<>(empleado);
            if (empleadoValidatorEntity.isError()) {
                BuilderAlert.buildAlert(empleadoValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
            } else {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                empleadoDAO.saveOrUpdate(empleado);
                this.close();
            }
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    @FXML
    void initialize() {
        assert nombre_empleado != null : "fx:id=\"nombre_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert apellido_empleado != null : "fx:id=\"apellido_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert direccion_empleado != null : "fx:id=\"direccion_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert dni_empleado != null : "fx:id=\"dni_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert edad_empleado != null : "fx:id=\"edad_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert correo_empleado != null : "fx:id=\"correo_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert registrar_empleado != null : "fx:id=\"registrar_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert cancelar_empleado != null : "fx:id=\"cancelar_empleado\" was not injected: check your FXML file 're_empleados.fxml'.";
        assert comb_rol != null : "fx:id=\"comb_rol\" was not injected: check your FXML file 're_empleados.fxml'.";
        try {
            RolDAO rolDAO = new RolDAO();
            comb_rol.setItems(FXCollections.observableList(rolDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    protected Empleado empleado = new Empleado();

    public EmpleadoAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/empleado/re_empleados.fxml"));
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
