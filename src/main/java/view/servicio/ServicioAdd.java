package view.servicio;

import controller.dao.DataAccessObjectException;
import controller.dao.InsumoDAO;
import controller.dao.ServicioDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Insumo;
import model.entity.Servicio;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ServicioAdd extends AbstractView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrar_servicio;

    @FXML
    private Button cancelar_servicios;

    @FXML
    protected ComboBox<Insumo> tipo_insumo_servicio;

    @FXML
    protected TextField nombre_servicio;

    @FXML
    protected TextArea descripcion_servicio;

    @FXML
    protected TextField precio_servicio;

    @FXML
    protected TextField cantidad_insumo_servicio;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar(ActionEvent event) {
        try {
            servicio.setDescripcionServicio(descripcion_servicio.getText());
            servicio.setNombreServicio(nombre_servicio.getText());
            if (tipo_insumo_servicio.getValue() != null) {
                servicio.setIdInsumo(tipo_insumo_servicio.getValue().getIdInsumo());
                servicio.setIdTipoInsumo(tipo_insumo_servicio.getValue().getIdTipoInsumo());
            }
            if (!precio_servicio.getText().equals("")) {
                servicio.setPrecioServicioXKg(Double.parseDouble(precio_servicio.getText()));
            }
            if (!cantidad_insumo_servicio.getText().equals("")) {
                servicio.setCantidadInsumo(Integer.parseInt(cantidad_insumo_servicio.getText()));
            }
            ValidatorEntity<Servicio> servicioValidatorEntity = new ValidatorEntity<>(servicio);
            if (servicioValidatorEntity.isError()) {
                BuilderAlert.buildAlert(servicioValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
            } else {

                ServicioDAO servicioDAO = new ServicioDAO();
                servicioDAO.saveOrUpdate(servicio);
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
        assert registrar_servicio != null : "fx:id=\"registrar_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert cancelar_servicios != null : "fx:id=\"cancelar_servicios\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert tipo_insumo_servicio != null : "fx:id=\"tipo_insumo_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert nombre_servicio != null : "fx:id=\"nombre_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert descripcion_servicio != null : "fx:id=\"descripcion_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert precio_servicio != null : "fx:id=\"precio_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        assert cantidad_insumo_servicio != null : "fx:id=\"cantidad_insumo_servicio\" was not injected: check your FXML file 're_servicios.fxml'.";
        try {
            InsumoDAO insumoDAO = new InsumoDAO();
            tipo_insumo_servicio.setItems(FXCollections.observableList(insumoDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    protected Servicio servicio = new Servicio();

    public ServicioAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/servicio/re_servicios.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Servicio");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
