package client.controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Clase controladora para la ventana LogOut
 *
 * @author UnaiUrtiaga
 */
public class VLogOutController {

    private final static Logger logger = Logger.getLogger("client.controllers.VLogOutController");
    
    private Stage stage;

    @FXML
    private Button btnLogOut, btnExit;
    @FXML
    private Pane logOutPane;
    @FXML
    private ImageView dedoOk;
    @FXML
    private Label textoWelcome;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo que se ejecutará una vez se abra la ventana para inicializarla
     *
     * @param root Nodo del grafo de la escena
     */
    public void initStage(Parent root) {

        logger.info("Iniciado el initStage de la ventana LogOut");
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("LogOut");
        
        //Aparecen una imagen y un texto
        this.dedoOk.setDisable(false);
        this.textoWelcome.setDisable(false);
        
        //El boton logOut y Exit se habilitan
        this.btnLogOut.setDisable(false);
        this.btnExit.setDisable(false);
        
        //Ventana no redimensionable
        stage.setResizable(false);

        //Accion de cerrar desde la barra de titulo
        stage.setOnCloseRequest(this::handleCloseRequest);

        stage.show();

    }

    /**
     * Método para cerrar sesión que se ejecutará una vez pulsado el botón
     * LogOut
     *
     * @param event Respresenta la accion del evento handleLogOut
     * @throws IOException Excepcion pedida por el metodo load
     */
    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {

        logger.info("Se ha pulsado el boton LogOut y se va a enviar un mensaje "
                + "de confirmacion al usuario");
        
        //Se envia un mensaje de confirmacion al usuario para cerrar sesion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("LOGOUT");

        alert.setContentText("Seguro que quiere cerrar sesión?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y la sesion se va a cerrar");
            
            //La ventana se cierra
            stage.close();
            
            //El usuario es dirigido a la ventana SignIn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));

            Parent root = loader.load();

            VSignInController controller = ((VSignInController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
            
        }else{
            logger.info("Se ha pulsado CANCELAR y el usuario va a seguir en la "
                    + "ventana actual");
            
            //El aviso se cierra y el usuario sigue en la ventana
            event.consume();
        }

    }

    /**
     * Método para salir de la aplicación que se ejecutará una vez pulsado el
     * botón Exit confirmando primero su salida
     *
     * @param event Respresenta la accion del evento handleExit
     */
    @FXML
    private void handleExitAction(ActionEvent event) {

        logger.info("El usuario ha pulsado el boton Exit y se va a enviar un "
                + "aviso de confirmacion");
        
        //Se envia un aviso de confirmacion al usuario para salir
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Seguro que quiere salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y el programa va a finalizar");
            
            //El programa finaliza
            stage.close();
        }else{
            logger.info("Se ha pulsado CANCELAR y el usuario va a seguir en la "
                    + "ventana actual");
            
            //El aviso se cierra y el usuario sigue en la ventana
            event.consume();
        }

    }
    /**
     * Método que controla la salida de la aplicación por el botón X de windows
     * preguntando al usuario si de verdad quiere salir
     *
     * @param event Respresenta la accion del evento handleCloseRequest
     */
    private void handleCloseRequest(WindowEvent event) {

        logger.info("El usuario ha pulsado el boton X de la barra de titulo y "
                + "se va a enviar un mensaje de confirmacion");
        
        //Se envia un aviso de confirmacion al usuario para salir
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Sure you want to go out?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y el programa va a finalizar");
            
            //El programa finaliza
            stage.close();
        } else {
            logger.info("Se ha pulsado CANCELAR y el usuario va a seguir en la "
                    + "ventana actual");
            
            //El aviso se cierra y el usuario sigue en la ventana
            event.consume();
        }

    }

}
