package view.login;

import controller.dao.DataAccessObjectException;
import controller.dao.EmpleadoDAO;
import controller.error.BuilderAlert;
import controller.hibernate.HibernateUtilException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.entity.Empleado;
import view.AbstractView;
import view.menu.Menu;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends AbstractView {

    private Empleado empleado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane fondo;

    @FXML
    private TextField textCorreo;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button btnInicioSesion;

    public Login(Stage stage) throws IOException {
        super(stage);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Login");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista login no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        String correo = textCorreo.getText();
        String password = textPassword.getText();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado;
        try {
            empleado = empleadoDAO.get_ByCorreo_ByPassword(correo, password);
            Menu menu = new Menu(thisStage, empleado);
            this.hide();
            menu.showAndWait();
        } catch (HibernateUtilException e) {
            BuilderAlert.buildAlert("No se encontro el usuario.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (DataAccessObjectException e) {
            BuilderAlert.buildAlert("No se pudo procesar la solicitud deseada.", e.getMessage(), Alert.AlertType.ERROR).show();
        } catch (NoResultException e) {
            BuilderAlert.buildAlert("El usuario no existe.", "El usuario con el correo: \"" + correo + "\" y con contraseña : \"" + password + "\"", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
    }

    @FXML
    void registrarNuevaPersona(ActionEvent event) throws IOException {
        Registro registro = new Registro(thisStage);
        registro.show();
    }

    @FXML
    void initialize() {
        assert fondo != null : "fx:id=\"fondo\" was not injected: check your FXML file 'login.fxml'.";
        assert textCorreo != null : "fx:id=\"textCorreo\" was not injected: check your FXML file 'login.fxml'.";
        assert textPassword != null : "fx:id=\"textPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert btnInicioSesion != null : "fx:id=\"btnInicioSesion\" was not injected: check your FXML file 'login.fxml'.";

    }
}