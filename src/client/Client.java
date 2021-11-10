package client;

import client.controllers.VSignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Clase principal la cual va a abrir la primera ventana SignIn
 * @author Unai Urtiaga
 */
public class Client extends Application{

    /**
     * Metodo main que va a ejecutar el metodo start
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo start para abrir la primera ventana de la aplicacion
     * @param primaryStage Parametro de tipo Stage el cual se va a utilizar para
     * darle un nuevo stage al controlador
     * @throws Exception Un controlador para la excepcion del load()
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));
        
        Parent root = loader.load();
        
        VSignInController controller = ((VSignInController)loader.getController());
        controller.setStage(primaryStage);
        controller.initStage(root);
        
    }
}