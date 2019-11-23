package view.recurso;

import controller.dao.DataAccessObjectException;
import controller.dao.RecursoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.Recurso;
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

public class RecursoIndex extends AbstractIndexView<Recurso> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_recurso_agregar;

    @FXML
    private Button btn_editar_recurso;

    @FXML
    private Button btn_eliminar_recurso;

    @FXML
    private Button btn_ver_recurso;

    @FXML
    private TableView<Recurso> tabla_recursos;


    @FXML
    void agregar_recurso(ActionEvent event) {
        try {
            RecursoAdd recursoAdd = new RecursoAdd(thisStage);
            recursoAdd.showAndWait();
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
    void editar_recurso(ActionEvent event) {
        if (tableViewEntity.getValueSelected() != null) {
            try {
                RecursoEdit recursoEdit = new RecursoEdit(thisStage, tableViewEntity.getValueSelected());
                recursoEdit.showAndWait();
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
    void eliminar_recurso(ActionEvent event) {
        if (tableViewEntity.getValueSelected() != null) {
            try {
                RecursoDAO recursoDAO = new RecursoDAO();
                recursoDAO.delete(tableViewEntity.getValueSelected());
                this.tableViewEntity.generateTableEntity();
            } catch (HibernateUtilException e) {
                BuilderAlert.buildAlert("No se encontro el recurso.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (DataAccessObjectException e) {
                BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
            } catch (Exception e) {
                BuilderAlert.buildAlertException(e).show();
            }
        }
    }

    @FXML
    void ver_recurso(ActionEvent event) {
        /*ESTO AUN NO SE IMPLEMENTO :::::::::(((((((*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_recursos.fxml'.";
        assert btn_recurso_agregar != null : "fx:id=\"btn_recurso_agregar\" was not injected: check your FXML file 'li_recursos.fxml'.";
        assert btn_editar_recurso != null : "fx:id=\"btn_editar_recurso\" was not injected: check your FXML file 'li_recursos.fxml'.";
        assert btn_eliminar_recurso != null : "fx:id=\"btn_eliminar_recurso\" was not injected: check your FXML file 'li_recursos.fxml'.";
        assert btn_ver_recurso != null : "fx:id=\"btn_ver_recurso\" was not injected: check your FXML file 'li_recursos.fxml'.";
        assert tabla_recursos != null : "fx:id=\"tabla_recursos\" was not injected: check your FXML file 'li_recursos.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Recurso.class.getSimpleName(), tabla_recursos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    public RecursoIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/recurso/li_recursos.fxml"));
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

    public RecursoIndex(Stage thisStage, Empleado empleado) {
        this(thisStage);
        this.empleado = empleado;
    }
}
