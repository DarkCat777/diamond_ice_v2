package view.empleado;

import javafx.stage.Stage;
import model.entity.Empleado;

public class EmpleadoEdit extends EmpleadoAdd {
    public EmpleadoEdit(Stage root, Empleado empleado) {
        super(root);
        this.empleado = empleado;
        this.comb_rol.setValue(empleado.getRolByIdRol());
        this.nombre_empleado.setText(empleado.getNombreEmpleado());
        this.apellido_empleado.setText(empleado.getApellidoEmpleado());
        this.direccion_empleado.setText(empleado.getDireccionEmpleado());
        this.correo_empleado.setText(empleado.getCorreoEmpleado());
        this.edad_empleado.setText(empleado.getPasswordEmpleado());
        this.dni_empleado.setText(empleado.getIdDniEmpleado() + "");
        this.dni_empleado.setDisable(true);
    }
}
