package view.insumo;

import controller.dao.DataAccessObjectException;
import controller.dao.InsumoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Insumo;
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

public class InsumoIndex extends AbstractIndexView<Insumo> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button insumo_agregar;

    @FXML
    private Button btn_editar_insumo;

    @FXML
    private Button btn_eliminar_insumo;

    @FXML
    private Button btn_ver_insumo;

    @FXML
    private TableView<Insumo> tabla_contenidos;


    @FXML
    void agregar_insumo(ActionEvent event) {
        try {
            InsumoAdd insumoAdd = new InsumoAdd(thisStage);
            insumoAdd.showAndWait();
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
    void editar_insumo(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                InsumoEdit insumoEdit = new InsumoEdit(thisStage, this.tableViewEntity.getValueSelected());
                insumoEdit.showAndWait();
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
    void eliminar_insumo(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                InsumoDAO insumoDAO = new InsumoDAO();
                insumoDAO.delete(this.tableViewEntity.getValueSelected());
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
    void ver_insumo(ActionEvent event) {
        /**NO SE IMPLEMENTO LA VISTA :::::::::((((((*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_insumos.fxml'.";
        assert insumo_agregar != null : "fx:id=\"insumo_agregar\" was not injected: check your FXML file 'li_insumos.fxml'.";
        assert btn_editar_insumo != null : "fx:id=\"btn_editar_insumo\" was not injected: check your FXML file 'li_insumos.fxml'.";
        assert btn_eliminar_insumo != null : "fx:id=\"btn_eliminar_insumo\" was not injected: check your FXML file 'li_insumos.fxml'.";
        assert btn_ver_insumo != null : "fx:id=\"btn_ver_insumo\" was not injected: check your FXML file 'li_insumos.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_insumos.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Insumo.class.getSimpleName(), tabla_contenidos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }


    public InsumoIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/insumo/li_insumos.fxml"));
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

    private Empleado empleado;

    public InsumoIndex(Stage thisStage, Empleado empleado) {
        this(thisStage);
        this.empleado = empleado;
    }
}
