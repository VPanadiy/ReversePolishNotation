package dream.development;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.dom.CollatorFactoryBase.DEFAULT_LOCALE;

/**
 * Boot class for application
 */
public class Main extends Application {

    /**
     * Method create primaryStage
     *
     * @param primaryStage Main stage
     * @throws IOException Resource not found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        Locale.setDefault(DEFAULT_LOCALE);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale"));

        Parent fxmlMain = fxmlLoader.load();

        Scene scene = new Scene(fxmlMain);

        primaryStage.setTitle(fxmlLoader.getResources().getString("main.title"));
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Boot method
     *
     * @param args Input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
