package view.correo;

import controller.error.BuilderAlert;
import controller.mail.MailService;
import controller.mail.PropertiesMailFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.Cliente;
import model.entity.Empleado;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CorreoView extends AbstractView {

    private Cliente cliente;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox fondo;

    @FXML
    private TextField asuntoCorreo;

    @FXML
    private ComboBox<Cliente> comboPersonas;

    @FXML
    private TextArea mensajeCorreo;

    @FXML
    private Button btnEnviar;

    public CorreoView(Stage root) {
        super(root);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/correo/correo.fxml"));
        loader.setController(this);
        try {
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Enviar Correo");
            thisStage.getIcons().add(new Image("/img/diamond_icon.png"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo cargar la vista solicitada.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = BuilderAlert.buildAlertException(e);
            alert.showAndWait();
        }
    }

    public CorreoView(Stage root, Cliente cliente) {
        super(root);
        this.cliente = cliente;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/correo/correo.fxml"));
        loader.setController(this);
        try {
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Enviar Correo");
            thisStage.getIcons().add(new Image("/img/diamond_icon.png"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo cargar la vista solicitada.");
            alert.showAndWait();
        }
        comboPersonas.setValue(cliente);
    }

    public CorreoView(Stage root, Empleado Empleado) {
        super(root);
        this.cliente = cliente;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/correo/correo.fxml"));
        loader.setController(this);
        try {
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Enviar Correo");
            thisStage.getIcons().add(new Image("/img/diamond_icon.png"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo cargar la vista solicitada.");
            alert.showAndWait();
        }
        comboPersonas.setValue(cliente);
    }

    @FXML
    void actualizarPersona(ActionEvent event) {
        this.cliente = comboPersonas.getValue();
    }

    @FXML
    void enviarCorreo(ActionEvent event) {
        MailService mailService = new MailService(PropertiesMailFactory.GMAIL, cliente.getCorreoCliente(), asuntoCorreo.getText(), mensajeCorreo.getText());
        mailService.sendMessage();
    }

    @FXML
    void initialize() {
        assert fondo != null : "fx:id=\"fondo\" was not injected: check your FXML file 'correo.fxml'.";
        assert asuntoCorreo != null : "fx:id=\"asuntoCorreo\" was not injected: check your FXML file 'correo.fxml'.";
        assert comboPersonas != null : "fx:id=\"comboPersonas\" was not injected: check your FXML file 'correo.fxml'.";
        assert mensajeCorreo != null : "fx:id=\"mensajeCorreo\" was not injected: check your FXML file 'correo.fxml'.";
        assert btnEnviar != null : "fx:id=\"btnEnviar\" was not injected: check your FXML file 'correo.fxml'.";
        /*PersonaDAO personaDAO = new PersonaDAO();
        try {
            List<Persona> personas = personaDAO.getAll();
            ObservableList<Persona> personasList = FXCollections.observableArrayList(personas);
            comboPersonas.setItems(personasList);
        } catch (HibernateUtilException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexion con la Base de Datos");
            alert.setContentText("No se ha podido establecer comunicacion con el servidor de la base de datos.");
            alert.showAndWait();
        } catch (DataAccessObjectException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No se escribieron los datos");
            alert.setContentText("Ha ocurrido un error con la operacion solicitada a la base de datos");
            alert.showAndWait();
        }
         */
    }
}
