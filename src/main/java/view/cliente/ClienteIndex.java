package view.cliente;

import controller.dao.ClienteDAO;
import controller.dao.DataAccessObjectException;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Cliente;
import model.entity.Empleado;
import view.AbstractIndexView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import view.correo.CorreoView;
import view.table.TableViewFactory;

public class ClienteIndex extends AbstractIndexView<Cliente> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane_1;

    @FXML
    private Button btn_agregar_clientes;

    @FXML
    private Button btn_enviar_correo;

    @FXML
    private Button btn_editar_cliente;

    @FXML
    private Button btn_eliminar_cliente;

    @FXML
    private Button btn_ver_cliente;

    @FXML
    private TableView<Cliente> tabla_clientes;

    @FXML
    void add_cliente(ActionEvent event) {
        try {
            ClienteAdd clienteAdd = new ClienteAdd(thisStage);
            clienteAdd.showAndWait();
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
    void delete_cliente(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.delete(this.tableViewEntity.getValueSelected());
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
    void editar_cliente(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            try {
                ClienteEdit clienteEdit = new ClienteEdit(thisStage, this.tableViewEntity.getValueSelected());
                clienteEdit.showAndWait();
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
    void enviar_correo(ActionEvent event) {
        if (this.tableViewEntity.getValueSelected() != null) {
            CorreoView correoView = new CorreoView(thisStage, this.tableViewEntity.getValueSelected());
            correoView.showAndWait();
        }
    }

    @FXML
    void view_cliente(ActionEvent event) {
        /*NO SE IMPLEMENTO VISTA :::::::::::::::(((((((((((((*/
    }

    @FXML
    void initialize() {
        assert pane_1 != null : "fx:id=\"pane_1\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert btn_agregar_clientes != null : "fx:id=\"btn_agregar_clientes\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert btn_enviar_correo != null : "fx:id=\"btn_enviar_correo\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert btn_editar_cliente != null : "fx:id=\"btn_editar_cliente\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert btn_eliminar_cliente != null : "fx:id=\"btn_eliminar_cliente\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert btn_ver_cliente != null : "fx:id=\"btn_ver_cliente\" was not injected: check your FXML file 'li_clientes.fxml'.";
        assert tabla_clientes != null : "fx:id=\"tabla_clientes\" was not injected: check your FXML file 'li_clientes.fxml'.";
        try {
            this.tableViewEntity = TableViewFactory.getTableView(Cliente.class.getSimpleName(), tabla_clientes);
            this.tableViewEntity.generateTableEntity();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }

    }


    public ClienteIndex(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cliente/li_clientes.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Cliente");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista recurso no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    private Empleado empleado;

    public ClienteIndex(Stage thisStage, Empleado empleado) {
        this(thisStage);
        this.empleado = empleado;
    }

}
