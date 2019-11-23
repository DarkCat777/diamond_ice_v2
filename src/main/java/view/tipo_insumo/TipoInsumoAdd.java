package view.tipo_insumo;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoInsumoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.TipoInsumo;
import view.AbstractView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class TipoInsumoAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrar_tipo_insumo;

    @FXML
    private Button cancelar_tipo_insumo;

    @FXML
    protected ComboBox<String> estado_registro;

    @FXML
    protected TextField nombre_tipo_insumo;

    @FXML
    protected TextArea descripcion_tipo_insumo;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar(ActionEvent event) {
        Boolean est_reg = null;
        if (opciones.get(0).equals(estado_registro.getValue())) {
            est_reg = true;
        } else if (opciones.get(1).equals(estado_registro.getValue())) {
            est_reg = false;
        }
        tipoInsumo.setNombreTipoInsumo(nombre_tipo_insumo.getText());
        tipoInsumo.setDescripcionTipoInsumo(descripcion_tipo_insumo.getText());
        tipoInsumo.setEstadoRegistro(est_reg);
        ValidatorEntity<TipoInsumo> tipoInsumoValidatorEntity = new ValidatorEntity<>(tipoInsumo);
        if (tipoInsumoValidatorEntity.isError()) {
            BuilderAlert.buildAlert(tipoInsumoValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
        } else {
            try {
                TipoInsumoDAO tipoInsumoDAO = new TipoInsumoDAO();
                tipoInsumoDAO.saveOrUpdate(tipoInsumo);
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
        assert registrar_tipo_insumo != null : "fx:id=\"registrar_tipo_insumo\" was not injected: check your FXML file 're_tipo_insumo.fxml'.";
        assert cancelar_tipo_insumo != null : "fx:id=\"cancelar_tipo_insumo\" was not injected: check your FXML file 're_tipo_insumo.fxml'.";
        assert estado_registro != null : "fx:id=\"estado_registro\" was not injected: check your FXML file 're_tipo_insumo.fxml'.";
        assert nombre_tipo_insumo != null : "fx:id=\"nombre_tipo_insumo\" was not injected: check your FXML file 're_tipo_insumo.fxml'.";
        assert descripcion_tipo_insumo != null : "fx:id=\"descripcion_tipo_insumo\" was not injected: check your FXML file 're_tipo_insumo.fxml'.";
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        estado_registro.setItems(stringsOpt);
        estado_registro.setValue(opciones.get(0));
    }

    protected List<String> opciones = new ArrayList<>();

    protected TipoInsumo tipoInsumo = new TipoInsumo();

    public TipoInsumoAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tipo_insumo/re_tipo_insumo.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Tipo Insumo");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
