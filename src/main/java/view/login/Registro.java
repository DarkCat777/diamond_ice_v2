package view.login;

import controller.dao.DataAccessObjectException;
import controller.dao.EmpleadoDAO;
import controller.dao.RolDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Rol;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Registro extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox fondo;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField apellidosUsuario;

    @FXML
    private TextField dniUsuario;

    @FXML
    private TextField direcionUsuario;

    @FXML
    private TextField correoUsuario;

    @FXML
    private PasswordField passwordUsuario;

    @FXML
    private ComboBox<Rol> comboRol;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnCancelar;

    @FXML
    void cancelarRegistro(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrarPersona(ActionEvent event) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado = new Empleado();
        try {
            if (!nombreUsuario.getText().equals("")) {
                empleado.setNombreEmpleado(nombreUsuario.getText());
            }
            if (!apellidosUsuario.getText().equals("")) {
                empleado.setApellidoEmpleado(apellidosUsuario.getText());
            }
            if (!correoUsuario.getText().equals("")) {
                empleado.setCorreoEmpleado(correoUsuario.getText());
            }
            if (!passwordUsuario.getText().equals("")) {
                empleado.setPasswordEmpleado(passwordUsuario.getText());
            }
            if (!direcionUsuario.getText().equals("")) {
                empleado.setDireccionEmpleado(direcionUsuario.getText());
            }
            if (!dniUsuario.getText().equals("")) {
                empleado.setIdDniEmpleado(Long.parseLong(dniUsuario.getText()));
            }
            if (comboRol.getValue() != null) {
                empleado.setIdRol(comboRol.getValue().getIdRol());
            }
            ValidatorEntity<Empleado> empleadoValidatorEntity = new ValidatorEntity<Empleado>(empleado);
            if (empleadoValidatorEntity.isError()) {
                BuilderAlert.buildAlert(empleadoValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
            } else {
                empleadoDAO.saveOrUpdate(empleado);
                thisStage.close();
            }
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No hay conexion con la base de datos.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (NumberFormatException e) {
            BuilderAlert.buildAlert("El dni debe ser de caracter numerico", "Introduzca su DNI de la forma (XXXXXXXX)", Alert.AlertType.INFORMATION).showAndWait();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert fondo != null : "fx:id=\"fondo\" was not injected: check your FXML file 'registro.fxml'.";
        assert nombreUsuario != null : "fx:id=\"nombreUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert apellidosUsuario != null : "fx:id=\"apellidosUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert dniUsuario != null : "fx:id=\"dniUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert direcionUsuario != null : "fx:id=\"direcionUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert correoUsuario != null : "fx:id=\"correoUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert passwordUsuario != null : "fx:id=\"passwordUsuario\" was not injected: check your FXML file 'registro.fxml'.";
        assert comboRol != null : "fx:id=\"comboRol\" was not injected: check your FXML file 'registro.fxml'.";
        assert btnRegistrar != null : "fx:id=\"btnRegistrar\" was not injected: check your FXML file 'registro.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'registro.fxml'.";
        cargarDatos();
    }

    private void cargarDatos() {
        RolDAO rolDAO = new RolDAO();
        try {
            this.comboRol.setItems(FXCollections.observableArrayList(rolDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    public Registro(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/registro.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Registro");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista registro no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
