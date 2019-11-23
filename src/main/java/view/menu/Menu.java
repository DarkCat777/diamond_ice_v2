package view.menu;

import controller.error.BuilderAlert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entity.Empleado;
import view.AbstractView;
import view.access.AccessIndex;
import view.cliente.ClienteIndex;
import view.empleado.EmpleadoIndex;
import view.insumo.InsumoIndex;
import view.orden_servicio.OrdenServicioIndex;
import view.recurso.RecursoIndex;
import view.rol.RolIndex;
import view.servicio.ServicioIndex;
import view.tipo_insumo.TipoInsumoIndex;
import view.tipo_prenda.TipoPrendaIndex;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu extends AbstractView {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_clientes;

    @FXML
    private Button btn_empleado;

    @FXML
    private Button btn_tipo_prenda;

    @FXML
    private Button btn_insumos;

    @FXML
    private Button btn_ordenes_servicio;

    @FXML
    private Button btn_servicio;

    @FXML
    private Button btn_accesos;

    @FXML
    private Button btn_tipo_insumo;

    @FXML
    private Button btn_recursos;

    @FXML
    private Button btn_rol;

    @FXML
    private Button btn_mi_cuenta;

    @FXML
    void accesos_menu(ActionEvent event) {
        AccessIndex accessIndex = new AccessIndex(thisStage, empleado);
        this.hide();
        accessIndex.showAndWait();
        this.show();
    }

    @FXML
    void cliente_menu(MouseEvent event) {
        ClienteIndex clienteIndex = new ClienteIndex(thisStage, empleado);
        this.hide();
        clienteIndex.showAndWait();
        this.show();
    }

    @FXML
    void clientes_menu(ActionEvent event) {
        ClienteIndex clienteIndex = new ClienteIndex(thisStage, empleado);
        this.hide();
        clienteIndex.showAndWait();
        this.show();
    }

    @FXML
    void empleados_menu(ActionEvent event) {
        EmpleadoIndex empleadoIndex = new EmpleadoIndex(thisStage, empleado);
        this.hide();
        empleadoIndex.showAndWait();
        this.show();
    }

    @FXML
    void insumos_menu(ActionEvent event) {
        InsumoIndex insumoIndex = new InsumoIndex(thisStage, empleado);
        this.hide();
        insumoIndex.showAndWait();
        this.show();
    }

    @FXML
    void mi_cuenta_view(ActionEvent event) {
        /*ME FALTA IMPLEMETAR ESTA PARTE*/
    }

    @FXML
    void ordenes_servicio_menu(ActionEvent event) {
        OrdenServicioIndex ordenServicioIndex = new OrdenServicioIndex(thisStage, empleado);
        this.close();
        ordenServicioIndex.showAndWait();
        this.show();
    }

    @FXML
    void prenda_menu(MouseEvent event) {
        TipoPrendaIndex tipoPrendaIndex = new TipoPrendaIndex(thisStage, empleado);
        this.hide();
        tipoPrendaIndex.showAndWait();
        this.show();
    }

    @FXML
    void rol_menu(ActionEvent event) {
        RolIndex rolIndex = new RolIndex(thisStage, empleado);
        this.hide();
        rolIndex.showAndWait();
        this.show();
    }

    @FXML
    void recursos_menu(ActionEvent event) {
        RecursoIndex recursoIndex = new RecursoIndex(thisStage, empleado);
        this.hide();
        recursoIndex.showAndWait();
        this.show();
    }


    @FXML
    void servicio_menu(ActionEvent event) {
        ServicioIndex servicioIndex = new ServicioIndex(thisStage, empleado);
        this.hide();
        servicioIndex.showAndWait();
        this.show();
    }

    @FXML
    void tipo_insumo_menu(ActionEvent event) {
        TipoInsumoIndex tipoInsumoIndex = new TipoInsumoIndex(thisStage, empleado);
        this.hide();
        tipoInsumoIndex.showAndWait();
        this.show();
    }

    @FXML
    void tipo_prenda_menu(ActionEvent event) {
        TipoPrendaIndex tipoPrendaIndex = new TipoPrendaIndex(thisStage, empleado);
        this.hide();
        tipoPrendaIndex.showAndWait();
        this.show();
    }

    @FXML
    void initialize() {
        assert btn_clientes != null : "fx:id=\"btn_clientes\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_empleado != null : "fx:id=\"btn_empleado\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_tipo_prenda != null : "fx:id=\"btn_tipo_prenda\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_insumos != null : "fx:id=\"btn_insumos\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_ordenes_servicio != null : "fx:id=\"btn_ordenes_servicio\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_servicio != null : "fx:id=\"btn_servicio\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_accesos != null : "fx:id=\"btn_accesos\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_tipo_insumo != null : "fx:id=\"btn_tipo_insumo\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_recursos != null : "fx:id=\"btn_recursos\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_rol != null : "fx:id=\"btn_rol\" was not injected: check your FXML file 'menu.fxml'.";
        assert btn_mi_cuenta != null : "fx:id=\"btn_mi_cuenta\" was not injected: check your FXML file 'menu.fxml'.";

    }

    private Empleado empleado;

    public Menu(Stage root, Empleado empleado) {
        super(root);
        this.empleado = empleado;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/menu.fxml"));
            loader.setController(this);
            thisStage.getIcons().add(new Image("/img/diamond-ice-logo.png"));
            thisStage.setTitle("Diamond Ice - Menu");
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
        } catch (IOException e) {
            BuilderAlert.buildAlert("No se pudo abrir la vista.", "La vista del Menu no se pudo cargar :(", Alert.AlertType.ERROR).show();
        } catch (Exception e) {
            BuilderAlert.buildAlertException(e).show();
        }
        thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                root.show();
            }
        });
    }
}
