package controller.error;

import controller.validator.AbstractValidatorEntity;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BuilderAlert {

    public static Alert buildAlert(String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Ha ocurrido un error :(");
        alert.setContentText(content);
        alert.setHeaderText(header);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        String url = "";
        switch (alertType) {
            case ERROR:
                url = "/img/error_icon.png";
                break;
            case WARNING:
                url = "/img/warning_icon.png";
                break;
            case INFORMATION:
                url = "/img/information_icon.png";
                break;
            case CONFIRMATION:
                url = "/img/confirmation_icon.png";
                break;
            default:
                url = "/img/diamond_icon.png";
                break;
        }
        stage.getIcons().add(new Image(url));
        return alert;
    }

    public static Alert buildAlert(AbstractValidatorEntity validatorEntity, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Ha ocurrido un error :(");
        alert.setContentText(validatorEntity.getContentValidationMessage());
        alert.setHeaderText(validatorEntity.getTitleValidationMessage());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        String url = "";
        switch (alertType) {
            case ERROR:
                url = "/img/error_icon.png";
                break;
            case WARNING:
                url = "/img/warning_icon.png";
                break;
            case INFORMATION:
                url = "/img/information_icon.png";
                break;
            case CONFIRMATION:
                url = "/img/confirmation_icon.png";
                break;
            default:
                url = "/img/diamond_icon.png";
                break;
        }
        stage.getIcons().add(new Image(url));
        return alert;
    }

    public static Alert buildAlertException(Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Este es el mensaje de error consulte con el administrador.");
        alert.setHeaderText("Un error ocurrio y no sabemos como controlarlo.");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("Ocurrio el siguiente error:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/img/error_icon.png"));
        return alert;
    }
}
