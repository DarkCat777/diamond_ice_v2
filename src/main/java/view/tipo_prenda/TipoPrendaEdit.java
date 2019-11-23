package view.tipo_prenda;

import javafx.stage.Stage;
import model.entity.TipoPrenda;

public class TipoPrendaEdit extends TipoPrendaAdd {
    public TipoPrendaEdit(Stage root, TipoPrenda tipoPrenda) {
        super(root);
        this.tipoPrenda = tipoPrenda;
        nombre_tipo_prenda.setText(tipoPrenda.getNombreTipoPrenda());
        descripcion_tipo_prenda.setText(tipoPrenda.getDescripcionTipoPrenda());
        String est_reg = opciones.get(2);
        if (tipoPrenda.getEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.estado_registro.setValue(est_reg);
    }
}
