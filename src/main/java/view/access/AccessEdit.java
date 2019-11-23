package view.access;

import javafx.stage.Stage;
import model.entity.Access;

public class AccessEdit extends AccessAdd {
    public AccessEdit(Stage root, Access access) {
        super(root);
        this.access = access;
        this.comb_recurso.setValue(access.getRecursoByIdRecurso());
        this.comb_rol_empleado.setValue(access.getRolByIdRol());
        String est_reg = opciones.get(2);
        if (access.isEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.comb_estado_registro.setValue(est_reg);
    }
}
