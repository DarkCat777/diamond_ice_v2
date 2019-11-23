package view.cliente;

import javafx.stage.Stage;
import model.entity.Cliente;

public class ClienteEdit extends ClienteAdd {
    public ClienteEdit(Stage root, Cliente cliente) {
        super(root);
        this.cliente = cliente;
        nombre_cliente.setText(cliente.getNombreCliente());
        apellido_cliente.setText(cliente.getApellidoCliente());
        direccion_cliente.setText(cliente.getDireccionCliente());
        dni_cliente.setText(cliente.getIdDniCliente() + "");
        edad_cliente.setText(cliente.getEdadCliente() + "");
        celular_cliente.setText(cliente.getCelularCliente() + "");
        correo_cliente.setText(cliente.getCorreoCliente());
    }
}
