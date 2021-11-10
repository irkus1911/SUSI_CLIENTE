package client.controllers;

import client.controllers.VLogOutController;
import client.controllers.VSignInController;
import client.factory.LogicableFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.dataModel.User;
import lib.dataModel.UserPrivilege;
import lib.dataModel.UserStatus;
import lib.exceptions.ConnectException;
import lib.exceptions.IncorrectEmailException;
import lib.exceptions.IncorrectPasswordException;
import lib.exceptions.IncorrectUserException;
import lib.exceptions.PasswordDontMatchException;
import lib.exceptions.TooManyUsersException;
import lib.exceptions.UserExistException;

/**
 * Clase controladora de la ventana singUp.
 * @author Steven Arce, Irkus De La Fuente
 */
public class VSignUpController {
    
    private final static Logger logger = Logger.getLogger("client.controllers.VSignUpController");
    private Stage stage;
    private Tooltip tooltip;
    
    @FXML
    private TextField fieldUsername, fieldEmail, fieldFullName;
    @FXML
    private PasswordField fieldPassword, fieldConfirmPassword;
    @FXML
    private Button buttonSignUp, buttonBack;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Este metodo pretende inicializar el la escena y el escenario.
     * @param root nodo del grafo de la escena
     */
    public void initStage(Parent root) {
        logger.info("Iniciado initStage de la ventana signUp");

        Scene scene = new Scene(root);
        stage.setTitle("SignUp");

        //Todos los campos y botones habilitados
        fieldUsername.setDisable(false);
        fieldEmail.setDisable(false);
        fieldFullName.setDisable(false);
        fieldPassword.setDisable(false);
        fieldConfirmPassword.setDisable(false);
        buttonSignUp.setDisable(false);
        buttonBack.setDisable(false);
        //Se enfoca en el campo username
        fieldUsername.requestFocus();
        //Esta ventana no se puede redimensionar
        stage.setResizable(false);

        //Tooltip en todos los campos para indicar lo que hay que introducir
        tooltip = new Tooltip("Introduce un nombre de usuario");
        fieldUsername.setTooltip(tooltip);
        tooltip = new Tooltip("Introduce un correo electronico");
        fieldEmail.setTooltip(tooltip);
        tooltip = new Tooltip("Introduce tu nombre completo");
        fieldFullName.setTooltip(tooltip);
        tooltip = new Tooltip("Introduce una contraseña");
        fieldPassword.setTooltip(tooltip);
        tooltip = new Tooltip("Repite la contraseña");
        fieldConfirmPassword.setTooltip(tooltip);

        //Controlador de evento para registrar un usuario
        buttonSignUp.setOnAction(this::handleSignUp);
        //Controlador de evento para volver a la ventana signIn
        buttonBack.setOnAction(this::handleBack);
        //Controlador de evento para cerrar desde la barra de título
        stage.setOnCloseRequest(this::handleCloseRequest);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Este metodo pretende validar todos los campos, si todo es valido mandara
     * un mensaje al servidor diciendo que hara un signUp. Si todo va bien, aparecera
     * una alerta diciendo que todo ha salido bien. En caso de haber algun error 
     * saldra una alerta indicando el tipo de error.
     * @param event representa la accion del evento handleSignUp
     */
    private void handleSignUp(ActionEvent event) {
        logger.info("Inicio del metodo para hacer signUp");

    }

    /**
     * Este metodo pretende regresar a la ventana singIn mediante la pulsacion 
     * del boton back.
     * @param event representa la accion del evento handleBack
     */
    private void handleBack(ActionEvent event) {
        logger.info("Se ha pulsado el boton back");
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));

        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            logger.info("Se ha producido un error al cargarel fxml de la ventana singIn");
        }

        VSignInController controller = (VSignInController) loader.getController();
        controller.setStage(stage);
        controller.initStage(root);

    }
    /**
     * Este metodo pretende controlar la salida de la ventana cuando se pulse la
     * X de la barra de titulo.
     * @param event representa la accion del evento handleCloseRequest
     */
    private void handleCloseRequest(WindowEvent event) {
        logger.info("Se ha pulsado la X de la barra de titulo y se enviara un "
                + "aviso de confirmacion");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Seguro que quiere salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y el programa va a finalizar");
            stage.close();
        } else {
            logger.info("Se ha cancelado el closeRequest");
            event.consume();
        }

    }

}
