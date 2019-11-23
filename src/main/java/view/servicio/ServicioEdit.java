package view.servicio;

import javafx.stage.Stage;
import model.entity.Servicio;

public class ServicioEdit extends ServicioAdd {
    public ServicioEdit(Stage root, Servicio servicio) {
        super(root);
        this.servicio = servicio;
        tipo_insumo_servicio.setValue(servicio.getInsumo());
        cantidad_insumo_servicio.setText(servicio.getCantidadInsumo()+"");
        precio_servicio.setText(servicio.getPrecioServicioXKg()+"");
        nombre_servicio.setText(servicio.getNombreServicio());
        descripcion_servicio.setText(servicio.getDescripcionServicio());
    }
}
