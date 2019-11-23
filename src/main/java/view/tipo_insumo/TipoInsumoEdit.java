package view.tipo_insumo;

import javafx.stage.Stage;
import model.entity.TipoInsumo;

public class TipoInsumoEdit extends TipoInsumoAdd {
    public TipoInsumoEdit(Stage root, TipoInsumo tipoInsumo) {
        super(root);
        this.tipoInsumo = tipoInsumo;
        this.descripcion_tipo_insumo.setText(tipoInsumo.getDescripcionTipoInsumo());
        this.nombre_tipo_insumo.setText(tipoInsumo.getNombreTipoInsumo());
        String est_reg = opciones.get(2);
        if (tipoInsumo.getEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.estado_registro.setValue(est_reg);
    }
}
