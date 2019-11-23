package view;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


public abstract class AbstractView {
    protected Stage root;
    protected Stage thisStage;

    public AbstractView(Stage root) {
        this.root = root;
        this.thisStage = new Stage(StageStyle.DECORATED);
    }

    public void show() {
        this.thisStage.show();
    }

    public void close() {
        this.thisStage.close();
    }

    public void hide() {
        thisStage.hide();
    }

    public void showAndWait() {
        thisStage.showAndWait();
    }
}
