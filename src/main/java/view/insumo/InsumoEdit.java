package view.insumo;

import javafx.stage.Stage;
import model.entity.Insumo;

public class InsumoEdit extends InsumoAdd {
    public InsumoEdit(Stage root, Insumo insumo) {
        super(root);
        this.insumo = insumo;
        String est_reg = opciones.get(2);
        if (insumo.getEstadoRegistro()) {
            est_reg = opciones.get(0);
        } else {
            est_reg = opciones.get(1);
        }
        this.estado_registro.setValue(est_reg);
        this.nombre_insumo.setText(insumo.getNombreInsumo());
        this.marca_insumo.setText(insumo.getMarcaInsumo());
        this.precio_insumo.setText(insumo.getPrecioInsumo() + "");
        this.stock_insumo.setText(insumo.getStockInsumo() + "");
        this.tipo_insumo.setValue(insumo.getTipoInsumoByIdTipoInsumo());
    }
}
