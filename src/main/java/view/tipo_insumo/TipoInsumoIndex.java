package view.tipo_insumo;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoInsumoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.TipoInsumo;
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

public class TipoInsumoIndex extends AbstractIndexView<TipoInsumo> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button tipo_insumo_agregar;

    @FXML
    private Button btn_editar_tipo_insumo;

    @FXML
    private Button btn_eliminar_tipo_insumo;

    @FXML
    private Button btn_ver_tipo_insumo;

    @FXML
    private TableView<TipoInsumo> tabla_contenidos;

    @FXML
    void agregar_tipo_insumo(ActionEvent event) {
        try {
            TipoInsumoAdd tipoInsumoAdd = new TipoInsumoAdd(thisStage);
            tipoInsumoAdd.showAndWait();
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
    void editar_tipo_insumo(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                TipoInsumoEdit tipoInsumoAdd = new TipoInsumoEdit(thisStage, this.tableViewEntity.getValueSelected());
                tipoInsumoAdd.showAndWait();
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
    void eliminar_tipo_insumo(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null)
            try {
                TipoInsumoDAO tipoInsumoDAO = new TipoInsumoDAO();
                tipoInsumoDAO.delete(this.tableViewEntity.getValueSelected());
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
    void ver_tipo_insumo(ActionEvent event) {
        /*VISTA NO IMPLEMENTADA*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        assert tipo_insumo_agregar != null : "fx:id=\"tipo_insumo_agregar\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        assert btn_editar_tipo_insumo != null : "fx:id=\"btn_editar_tipo_insumo\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        assert btn_eliminar_tipo_insumo != null : "fx:id=\"btn_eliminar_tipo_insumo\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        assert btn_ver_tipo_insumo != null : "fx:id=\"btn_ver_tipo_insumo\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_tipo_insumo.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(TipoInsumo.class.getSimpleName(), tabla_contenidos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    public TipoInsumoIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tipo_insumo/li_tipo_insumo.fxml"));
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

    private Empleado empleado;

    public TipoInsumoIndex(Stage thisStage, Empleado empleado) {
        this(thisStage);
        this.empleado = empleado;
    }
}
