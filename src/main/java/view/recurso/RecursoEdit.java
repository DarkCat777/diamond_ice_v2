package view.recurso;

import javafx.stage.Stage;
import model.entity.Recurso;

public class RecursoEdit extends RecursoAdd {

    public RecursoEdit(Stage root, Recurso recurso) {
        super(root);
        this.recurso = recurso;
        this.nombre_recurso.setText(recurso.getNombreRecurso());
        String est_reg = opciones.get(2);
        if (recurso.isEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.estado_registro.setValue(est_reg);
    }
}
