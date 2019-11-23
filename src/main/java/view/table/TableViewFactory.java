package view.table;

import controller.dao.DataAccessObjectException;
import controller.hibernate.HibernateUtilException;
import javafx.scene.control.TableView;
import model.entity.*;

public class TableViewFactory {
    public static TableViewEntity getTableView(String aClass, TableView<?> tableView) throws HibernateUtilException, DataAccessObjectException {
        if (aClass.equals(Access.class.getSimpleName())) {
            return new AccessTableView((TableView<Access>) tableView);
        }
        if (aClass.equals(Cliente.class.getSimpleName())) {
            return new ClienteTableView((TableView<Cliente>) tableView);
        }
        if (aClass.equals(Empleado.class.getSimpleName())) {
            return new EmpleadoTableView((TableView<Empleado>) tableView);
        }
        if (aClass.equals(Insumo.class.getSimpleName())) {
            return new InsumoTableView((TableView<Insumo>) tableView);
        }
        if (aClass.equals(Recurso.class.getSimpleName())) {
            return new RecursoTableView((TableView<Recurso>) tableView);
        }
        if (aClass.equals(Rol.class.getSimpleName())) {
            return new RolTableView((TableView<Rol>) tableView);
        }
        if (aClass.equals(Servicio.class.getSimpleName())) {
            return new ServicioTableView((TableView<Servicio>) tableView);
        }
        if (aClass.equals(TipoInsumo.class.getSimpleName())) {
            return new TipoInsumoTableView((TableView<TipoInsumo>) tableView);
        }
        if (aClass.equals(TipoPrenda.class.getSimpleName())) {
            return new TipoPrendaTableView((TableView<TipoPrenda>) tableView);
        }
        if (aClass.equals(OrdenServicioDet.class.getSimpleName())) {
            return new OrdenServicioDetTableView((TableView<OrdenServicioDet>) tableView);
        }
        if (aClass.equals(OrdenServicioCab.class.getSimpleName())) {
            return new OrdenServicioCabTableView((TableView<OrdenServicioCab>) tableView);
        }
        System.out.println("FAIL");
        return null;
    }
}
