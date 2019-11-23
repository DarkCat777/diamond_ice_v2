package view.tipo_prenda;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoPrendaDAO;
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
import model.entity.TipoPrenda;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TipoPrendaAdd extends AbstractView {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrar_tipo_prenda;

    @FXML
    private Button cancelar_tipo_prenda;

    @FXML
    protected ComboBox<String> estado_registro;

    @FXML
    protected TextField nombre_tipo_prenda;

    @FXML
    protected TextArea descripcion_tipo_prenda;

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
        tipoPrenda.setNombreTipoPrenda(nombre_tipo_prenda.getText());
        tipoPrenda.setDescripcionTipoPrenda(descripcion_tipo_prenda.getText());
        tipoPrenda.setEstadoRegistro(est_reg);
        ValidatorEntity<TipoPrenda> validatorEntity = new ValidatorEntity<>(tipoPrenda);
        if (validatorEntity.isError()) {
            BuilderAlert.buildAlert(validatorEntity, Alert.AlertType.INFORMATION);
        } else {
            try {
                TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO();
                tipoPrendaDAO.saveOrUpdate(tipoPrenda);
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
        assert registrar_tipo_prenda != null : "fx:id=\"registrar_tipo_prenda\" was not injected: check your FXML file 're_tipo_prenda.fxml'.";
        assert cancelar_tipo_prenda != null : "fx:id=\"cancelar_tipo_prenda\" was not injected: check your FXML file 're_tipo_prenda.fxml'.";
        assert estado_registro != null : "fx:id=\"estado_registro\" was not injected: check your FXML file 're_tipo_prenda.fxml'.";
        assert nombre_tipo_prenda != null : "fx:id=\"nombre_tipo_prenda\" was not injected: check your FXML file 're_tipo_prenda.fxml'.";
        assert descripcion_tipo_prenda != null : "fx:id=\"descripcion_tipo_prenda\" was not injected: check your FXML file 're_tipo_prenda.fxml'.";
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        estado_registro.setItems(stringsOpt);
        estado_registro.setValue(opciones.get(0));
    }

    protected List<String> opciones = new ArrayList<>();

    protected TipoPrenda tipoPrenda = new TipoPrenda();

    public TipoPrendaAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tipo_prenda/re_tipo_prenda.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Tipo Prenda");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
