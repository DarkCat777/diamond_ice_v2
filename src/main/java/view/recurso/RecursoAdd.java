package view.recurso;

import controller.dao.DataAccessObjectException;
import controller.dao.RecursoDAO;
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
import model.entity.Recurso;
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
import javafx.scene.control.TextField;

public class RecursoAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_registrar_recurso;

    @FXML
    private Button cancelar_recurso;

    @FXML
    protected ComboBox<String> estado_registro;

    @FXML
    protected TextField nombre_recurso;

    @FXML
    protected void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    protected void registrar_recurso(ActionEvent event) {
        Boolean est_reg = null;
        if (opciones.get(0).equals(estado_registro.getValue())) {
            est_reg = true;
        } else if (opciones.get(1).equals(estado_registro.getValue())) {
            est_reg = false;
        }
        recurso.setNombreRecurso(nombre_recurso.getText());
        recurso.setEstadoRegistro(est_reg);
        ValidatorEntity<Recurso> recursoValidatorEntity = new ValidatorEntity<>(recurso);
        if (recursoValidatorEntity.isError()) {
            BuilderAlert.buildAlert(recursoValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
        } else {
            try {
                RecursoDAO recursoDAO = new RecursoDAO();
                recursoDAO.saveOrUpdate(recurso);
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
        assert btn_registrar_recurso != null : "fx:id=\"btn_registrar_recurso\" was not injected: check your FXML file 're_recursos.fxml'.";
        assert cancelar_recurso != null : "fx:id=\"cancelar_recurso\" was not injected: check your FXML file 're_recursos.fxml'.";
        assert estado_registro != null : "fx:id=\"estado_registro\" was not injected: check your FXML file 're_recursos.fxml'.";
        assert nombre_recurso != null : "fx:id=\"nombre_recurso\" was not injected: check your FXML file 're_recursos.fxml'.";
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        estado_registro.setItems(stringsOpt);
        estado_registro.setValue(opciones.get(0));
    }

    protected List<String> opciones = new ArrayList<>();

    protected Recurso recurso = new Recurso();

    public RecursoAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/recurso/re_recursos.fxml"));
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
