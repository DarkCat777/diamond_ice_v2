
import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import javafx.application.Application;
import javafx.stage.Stage;
import view.login.Login;

import java.io.IOException;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			HibernateUtil.buildSessionFactory();
			Login login = new Login(primaryStage);
			login.show();
		} catch (HibernateUtilException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		HibernateUtil.closeSessionFactory();
	}

}
