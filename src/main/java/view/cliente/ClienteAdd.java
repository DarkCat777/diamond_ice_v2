package view.cliente;

import controller.dao.ClienteDAO;
import controller.dao.DataAccessObjectException;
import controller.dao.RecursoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import controller.validator.ValidatorEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entity.Cliente;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClienteAdd extends AbstractView {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected TextField nombre_cliente;

    @FXML
    protected TextField apellido_cliente;

    @FXML
    protected TextField direccion_cliente;

    @FXML
    protected TextField dni_cliente;

    @FXML
    protected TextField edad_cliente;

    @FXML
    protected TextField celular_cliente;

    @FXML
    protected TextField correo_cliente;

    @FXML
    private Button btn_registrar_cliente;

    @FXML
    private Button btn_cancelar;

    @FXML
    void cancelar(ActionEvent event) {
        this.close();
    }

    @FXML
    protected void registrar_cliente(ActionEvent event) {
        cliente.setNombreCliente(nombre_cliente.getText());
        cliente.setApellidoCliente(apellido_cliente.getText());
        cliente.setDireccionCliente(direccion_cliente.getText());
        cliente.setCorreoCliente(correo_cliente.getText());
        if (!dni_cliente.getText().equals("")) {
            cliente.setIdDniCliente(Long.parseLong(dni_cliente.getText()));
        }
        if (!edad_cliente.getText().equals("")) {
            cliente.setEdadCliente(Integer.parseInt(edad_cliente.getText()));
        }
        if (!celular_cliente.getText().equals("")) {
            cliente.setCelularCliente(Long.parseLong(celular_cliente.getText()));
        }
        ValidatorEntity<Cliente> clienteValidatorEntity = new ValidatorEntity<>(cliente);
        if (clienteValidatorEntity.isError()) {
            BuilderAlert.buildAlert(clienteValidatorEntity, Alert.AlertType.INFORMATION).showAndWait();
        } else {
            try {
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.saveOrUpdate(cliente);
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
        assert nombre_cliente != null : "fx:id=\"nombre_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert apellido_cliente != null : "fx:id=\"apellido_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert direccion_cliente != null : "fx:id=\"direccion_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert dni_cliente != null : "fx:id=\"dni_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert edad_cliente != null : "fx:id=\"edad_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert celular_cliente != null : "fx:id=\"celular_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert correo_cliente != null : "fx:id=\"correo_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert btn_registrar_cliente != null : "fx:id=\"btn_registrar_cliente\" was not injected: check your FXML file 're_clientes.fxml'.";
        assert btn_cancelar != null : "fx:id=\"btn_cancelar\" was not injected: check your FXML file 're_clientes.fxml'.";
    }

    protected Cliente cliente = new Cliente();

    public Cliente getCliente() {
        return cliente;
    }

    public ClienteAdd(Stage root) {
        super(root);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cliente/re_clientes.fxml"));
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
}
