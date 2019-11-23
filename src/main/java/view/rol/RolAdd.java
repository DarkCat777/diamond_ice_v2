package view.rol;

import controller.dao.DataAccessObjectException;
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
import model.entity.Rol;
import view.AbstractView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RolAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_registrar_rol;

    @FXML
    private Button cancelar_tipo_persona;

    @FXML
    protected ComboBox<String> estado_registro;

    @FXML
    protected TextField nombre_rol;

    @FXML
    protected TextArea descripcion_rol;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar_rol(ActionEvent event) {
        Boolean est_reg = null;
        if (opciones.get(0).equals(estado_registro.getValue())) {
            est_reg = true;
        } else if (opciones.get(1).equals(estado_registro.getValue())) {
            est_reg = false;
        }
        rol.setNombreRol(nombre_rol.getText());
        rol.setDescripcionRol(descripcion_rol.getText());
        rol.setEstadoRegistro(est_reg);
        ValidatorEntity<Rol> validatorEntity = new ValidatorEntity<>(rol);
        if (validatorEntity.isError()) {
            BuilderAlert.buildAlert(validatorEntity, Alert.AlertType.INFORMATION).showAndWait();
        } else {
            try {
                RolDAO rolDAO = new RolDAO();
                rolDAO.saveOrUpdate(rol);
                this.close();
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
    void initialize() {
        assert btn_registrar_rol != null : "fx:id=\"btn_registrar_rol\" was not injected: check your FXML file 're_rol.fxml'.";
        assert cancelar_tipo_persona != null : "fx:id=\"cancelar_tipo_persona\" was not injected: check your FXML file 're_rol.fxml'.";
        assert estado_registro != null : "fx:id=\"estado_registro\" was not injected: check your FXML file 're_rol.fxml'.";
        assert nombre_rol != null : "fx:id=\"nombre_rol\" was not injected: check your FXML file 're_rol.fxml'.";
        assert descripcion_rol != null : "fx:id=\"descripcion_rol\" was not injected: check your FXML file 're_rol.fxml'.";
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        estado_registro.setItems(stringsOpt);
        estado_registro.setValue(opciones.get(0));
    }

    protected List<String> opciones = new ArrayList<>();

    protected Rol rol = new Rol();


    public RolAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rol/re_rol.fxml"));
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
