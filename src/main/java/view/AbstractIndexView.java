package view;

import javafx.stage.Stage;
import view.table.TableViewEntity;

public abstract class AbstractIndexView<T> extends AbstractView {
    protected TableViewEntity<T> tableViewEntity;

    public AbstractIndexView(Stage root) {
        super(root);
    }
}