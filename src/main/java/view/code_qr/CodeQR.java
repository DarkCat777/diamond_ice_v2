package view.code_qr;

import controller.qr.QRGenerator;
import controller.qr.QRGeneratorException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.AbstractView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CodeQR extends AbstractView {

    private String str_for_QR;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox fondo;

    @FXML
    private Text titulo;

    @FXML
    private HBox fondoQR;

    @FXML
    private ImageView imgCodigoQR;

    @FXML
    void initialize() {
        assert fondo != null : "fx:id=\"fondo\" was not injected: check your FXML file 'codeQR.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'codeQR.fxml'.";
        assert fondoQR != null : "fx:id=\"fondoQR\" was not injected: check your FXML file 'codeQR.fxml'.";
        assert imgCodigoQR != null : "fx:id=\"imgCodigoQR\" was not injected: check your FXML file 'codeQR.fxml'.";
        //generarImagenQR();
        cargarImagenQR();
    }

    private void generarImagenQR() {
        QRGenerator qrGenerator = QRGenerator.getQRGenerator();
        try {
            qrGenerator.GenerateCodeQR(str_for_QR);
        } catch (QRGeneratorException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo generar el codigo QR.");
            alert.showAndWait();
        }
    }

    private void cargarImagenQR() {
        Image qr = new Image("file:temporal_QR.jpg");
        imgCodigoQR.setImage(qr);
    }

    public CodeQR(Stage root) {
        super(root);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/codeQR/codeQR.fxml"));
        loader.setController(this);
        try {
            thisStage.setScene(new Scene(loader.load()));
            thisStage.getIcons().add(new Image("/img/diamond_icon.png"));
            thisStage.setTitle("Codigo QR");
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo cargar la vista solicitada.");
            alert.showAndWait();
        }
    }

    public CodeQR(Stage root, String qr) {
        super(root);
        this.str_for_QR = qr;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/codeQR/codeQR.fxml"));
        loader.setController(this);
        try {
            thisStage.setScene(new Scene(loader.load()));
            thisStage.getIcons().add(new Image("/img/diamond_icon.png"));
            thisStage.setTitle("Codigo QR");
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se pudo cargar la vista solicitada.");
            alert.showAndWait();
        }
        generarImagenQR();
        cargarImagenQR();
    }

}
