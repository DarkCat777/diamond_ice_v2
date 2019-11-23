package view.rol;

import javafx.stage.Stage;
import model.entity.Rol;

public class RolEdit extends RolAdd {
    public RolEdit(Stage root, Rol rol) {
        super(root);
        this.rol = rol;
        this.nombre_rol.setText(rol.getNombreRol());
        this.descripcion_rol.setText(rol.getDescripcionRol());
        String est_reg = opciones.get(2);
        if (rol.getEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.estado_registro.setValue(est_reg);
    }
}
