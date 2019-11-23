package view.tipo_prenda;

import controller.dao.DataAccessObjectException;
import controller.dao.TipoPrendaDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Empleado;
import model.entity.TipoPrenda;
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

public class TipoPrendaIndex extends AbstractIndexView<TipoPrenda> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_tipo_prenda_agregar;

    @FXML
    private Button btn_editar_tipo_prenda;

    @FXML
    private Button btn_eliminar_tipo_prenda;

    @FXML
    private Button btn_ver_tipo_prenda;

    @FXML
    private TableView<TipoPrenda> tabla_contenidos;

    @FXML
    void agregar_tipo_prenda(ActionEvent event) {
        try {
            TipoPrendaAdd tipoPrendaAdd = new TipoPrendaAdd(thisStage);
            tipoPrendaAdd.showAndWait();
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
    void editar_tipo_prenda(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                TipoPrendaEdit tipoPrendaEdit = new TipoPrendaEdit(thisStage, this.tableViewEntity.getValueSelected());
                tipoPrendaEdit.showAndWait();
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
    void eliminar_tipo_prenda(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO();
                tipoPrendaDAO.delete(tableViewEntity.getValueSelected());
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
    void ver_tipo_prenda(ActionEvent event) {
        /*VISTA NO IMPLEMENTADA*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        assert btn_tipo_prenda_agregar != null : "fx:id=\"btn_tipo_prenda_agregar\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        assert btn_editar_tipo_prenda != null : "fx:id=\"btn_editar_tipo_prenda\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        assert btn_eliminar_tipo_prenda != null : "fx:id=\"btn_eliminar_tipo_prenda\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        assert btn_ver_tipo_prenda != null : "fx:id=\"btn_ver_tipo_prenda\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        assert tabla_contenidos != null : "fx:id=\"tabla_contenidos\" was not injected: check your FXML file 'li_tipo_prenda.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(TipoPrenda.class.getSimpleName(), tabla_contenidos);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    protected Empleado empleado;

    public TipoPrendaIndex(Stage root, Empleado empleado) {
        this(root);
        this.empleado = empleado;
    }

    public TipoPrendaIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tipo_prenda/li_tipo_prenda.fxml"));
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
