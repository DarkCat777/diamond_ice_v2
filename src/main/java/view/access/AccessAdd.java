package view.access;

import controller.dao.AccessDAO;
import controller.dao.DataAccessObjectException;
import controller.dao.RecursoDAO;
import controller.dao.RolDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Access;
import model.entity.Recurso;
import model.entity.Rol;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class AccessAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_registrar_acceso;

    @FXML
    private Button cancelar_acceso;

    @FXML
    protected ComboBox<Rol> comb_rol_empleado;

    @FXML
    protected ComboBox<Recurso> comb_recurso;

    @FXML
    protected ComboBox<String> comb_estado_registro;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar_acceso(ActionEvent event) {
        Boolean est_reg = null;
        if (opciones.get(0).equals(comb_estado_registro.getValue())) {
            est_reg = true;
        } else if (opciones.get(1).equals(comb_estado_registro.getValue())) {
            est_reg = false;
        }
        if (comb_recurso.getValue() != null)
            access.setIdRecurso(comb_recurso.getValue().getIdRecurso());
        if (comb_rol_empleado.getValue() != null)
            access.setIdRol(comb_rol_empleado.getValue().getIdRol());
        access.setEstadoRegistro(est_reg);
        ValidatorEntity<Access> accessValidatorEntity = new ValidatorEntity<>(access);
        if (accessValidatorEntity.isError()) {
            BuilderAlert.buildAlert(accessValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
        } else {
            try {
                AccessDAO accessDAO = new AccessDAO();
                accessDAO.saveOrUpdate(access);
                this.close();
            } catch (HibernateUtilException e) {
                BuilderAlert.buildAlert("No logro conectar con la base de datos.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (DataAccessObjectException e) {
                BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (Exception e) {
                BuilderAlert.buildAlertException(e).show();
            }
        }
    }

    @FXML
    void initialize() {
        assert btn_registrar_acceso != null : "fx:id=\"btn_registrar_acceso\" was not injected: check your FXML file 're_accesos.fxml'.";
        assert cancelar_acceso != null : "fx:id=\"cancelar_acceso\" was not injected: check your FXML file 're_accesos.fxml'.";
        assert comb_rol_empleado != null : "fx:id=\"comb_rol_empleado\" was not injected: check your FXML file 're_accesos.fxml'.";
        assert comb_recurso != null : "fx:id=\"comb_recurso\" was not injected: check your FXML file 're_accesos.fxml'.";
        assert comb_estado_registro != null : "fx:id=\"comb_estado_registro\" was not injected: check your FXML file 're_accesos.fxml'.";
        cargarDatos();
    }

    protected void cargarDatos() {
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        comb_estado_registro.setItems(stringsOpt);
        comb_estado_registro.setValue(opciones.get(0));
        try {
            RolDAO rolDAO = new RolDAO();
            comb_rol_empleado.setItems(FXCollections.observableList(rolDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No logro conectar con la base de datos.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
        try {
            RecursoDAO recursoDAO = new RecursoDAO();
            comb_recurso.setItems(FXCollections.observableList(recursoDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No logro conectar con la base de datos.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    protected List<String> opciones = new ArrayList<>();

    protected Access access = new Access();

    public AccessAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/access/re_accesos.fxml"));
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
}
