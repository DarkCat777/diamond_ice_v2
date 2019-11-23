package view.table;

import controller.dao.DataAccessObjectException;
import controller.hibernate.HibernateUtilException;
import javafx.scene.control.TableView;

public abstract class TableViewEntity<T> {
    TableView<T> tableViewEntity;

    public TableViewEntity(TableView<T> tableView) {
        this.tableViewEntity = tableView;
    }

    public abstract void generateTableEntity() throws HibernateUtilException, DataAccessObjectException;

    public abstract void chargeColumn();

    public abstract void chargeValues() throws DataAccessObjectException, HibernateUtilException;

    public TableView<T> getGeneratedTable() {
        return this.tableViewEntity;
    }

    public T getValueSelected() {
        return tableViewEntity.getSelectionModel().getSelectedItem();
    }
}
