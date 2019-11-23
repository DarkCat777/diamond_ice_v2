package view.insumo;

import controller.dao.DataAccessObjectException;
import controller.dao.InsumoDAO;
import controller.dao.TipoInsumoDAO;
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
import model.entity.Insumo;
import model.entity.TipoInsumo;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class InsumoAdd extends AbstractView {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected TextField nombre_insumo;

    @FXML
    protected TextField precio_insumo;

    @FXML
    protected TextField stock_insumo;

    @FXML
    private Button btn_registrar_insumos;

    @FXML
    private Button btn_cancelar_insumo;

    @FXML
    protected ComboBox<TipoInsumo> tipo_insumo;

    @FXML
    protected TextField marca_insumo;

    @FXML
    protected ComboBox<String> estado_registro;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    void registrar_insumo(ActionEvent event) {
        try {
            Boolean est_reg = null;
            if (opciones.get(0).equals(estado_registro.getValue())) {
                est_reg = true;
            } else if (opciones.get(1).equals(estado_registro.getValue())) {
                est_reg = false;
            }
            insumo.setNombreInsumo(nombre_insumo.getText());
            insumo.setMarcaInsumo(marca_insumo.getText());
            insumo.setEstadoRegistro(est_reg);
            if (tipo_insumo.getValue() != null) {
                insumo.setIdTipoInsumo(tipo_insumo.getValue().getIdTipoInsumo());
            }
            if (!precio_insumo.getText().equals("")) {
                insumo.setPrecioInsumo(Double.parseDouble(precio_insumo.getText()));
            }
            if (!stock_insumo.getText().equals("")) {
                insumo.setStockInsumo(Integer.parseInt(stock_insumo.getText()));
            }
            ValidatorEntity<Insumo> insumoValidatorEntity = new ValidatorEntity<>(insumo);
            if (insumoValidatorEntity.isError()) {
                BuilderAlert.buildAlert(insumoValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
            } else {
                InsumoDAO insumoDAO = new InsumoDAO();
                insumoDAO.saveOrUpdate(insumo);
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
        assert nombre_insumo != null : "fx:id=\"nombre_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert precio_insumo != null : "fx:id=\"precio_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert stock_insumo != null : "fx:id=\"stock_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert btn_registrar_insumos != null : "fx:id=\"btn_registrar_insumos\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert btn_cancelar_insumo != null : "fx:id=\"btn_cancelar_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert tipo_insumo != null : "fx:id=\"tipo_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert marca_insumo != null : "fx:id=\"marca_insumo\" was not injected: check your FXML file 're_insumos.fxml'.";
        assert estado_registro != null : "fx:id=\"estado_registro\" was not injected: check your FXML file 're_insumos.fxml'.";
        cargarDatos();
    }

    private void cargarDatos() {
        opciones.add("Activo");
        opciones.add("Inactivo");
        opciones.add("Deshabilitado");
        ObservableList<String> stringsOpt = FXCollections.observableArrayList(opciones);
        estado_registro.setItems(stringsOpt);
        estado_registro.setValue(opciones.get(0));
        try {
            TipoInsumoDAO tipoInsumoDAO = new TipoInsumoDAO();
            tipo_insumo.setItems(FXCollections.observableList(tipoInsumoDAO.getAll()));
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    protected ArrayList<String> opciones = new ArrayList<>();

    protected Insumo insumo = new Insumo();

    public InsumoAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insumo/re_insumos.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Insumo");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }
}
