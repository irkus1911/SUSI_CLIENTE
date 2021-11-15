package client.controllers;

import client.factory.LogicableFactory;
import java.io.IOException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.dataModel.User;
import lib.exceptions.ConnectException;
import lib.exceptions.IncorrectPasswordException;
import lib.exceptions.IncorrectUserException;
import lib.exceptions.PasswordDontMatchException;
import lib.exceptions.TooManyUsersException;
import lib.exceptions.UserDontExistException;

/**
 * Clase controladora para la ventana de SignIn
 *
 * @author UnaiUrtiaga, AdrianFranco
 */
public class VSignInController {

    private Tooltip tooltip;
    private final static Logger logger = Logger.getLogger("client.controllers.VSignInController");
    
    private Stage stage;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Hyperlink hlSignUp;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo initStage el cual se va a ejecutar una vez se abra la ventana para
     * inicializarla
     *
     * @param root Nodo del grafo de la escena
     */
    public void initStage(Parent root) {

        logger.info("Iniciado el initStage de la ventana SignIn");
        
        Scene scene = new Scene(root);    

        stage.setScene(scene);
        stage.setTitle("SignIn");

        //Campos habilitados
        this.txtUsername.setDisable(false);
        this.txtPassword.setDisable(false);
        
        //Boton logIn habilitado
        this.btnSignIn.setDisable(false);
        
        //Hyperlink Not a member? habilitado
        this.hlSignUp.setDisable(false);
        
        
        //Se enfoca en el campo username
        this.txtUsername.requestFocus();
        
        //Ventana no redimensionable
        stage.setResizable(false);
        
        //Tooltip encima de todos los campos
        tooltip = new Tooltip("Introduce un nombre de usuario");
        this.txtUsername.setTooltip(tooltip);
        tooltip = new Tooltip("Introduce una contraseña");
        this.txtPassword.setTooltip(tooltip);

        
        
        //Accion de cerrar desde la barra de titulo
        stage.setOnCloseRequest(this::handleCloseRequest);

        stage.show();

    }

    /**
     * Metodo que se va a ejecutar una vez pulsado el boton signIn en el cual se
     * controlan todos los campos y excepciones posibles
     *
     * @param event Respresenta la accion del evento handleSignIn
     */
    @FXML
    private void handleSignInAction(ActionEvent event) {
        
        logger.info("Se ha pulsado el boton de inicio de sesion y se intentara"
                + "iniciar sesion");
        
        try {
            //Validamos que los campos username y password estan informados con informedFields()
            //Validamos que la longitud de los dos campos no superen los 50 caracteres con maxCharacters()
            if (informedFields() && maxCharacteres()) {
                User user = new User();
                user.setLogin(txtUsername.getText());
                user.setPassword(txtPassword.getText());

                LogicableFactory dataFac = new LogicableFactory();

                if (dataFac.getDataTraffic().signIn(user) != null) {
                    //La ventana actual se cierra
                    stage.close();
                    
                    //El usuario inicia sesion y va a la ventana LogOut
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VLogOut.fxml"));

                    Parent root = loader.load();
                    Logger.getLogger(VSignInController.class.getName()).info("LOGOUT VENTANA");
                    
                    VLogOutController controller = ((VLogOutController) loader.getController());
                    controller.mensajePersonalizado(dataFac.getDataTraffic().signIn(user));
                    controller.setStage(stage);
                    controller.initStage(root);
                }
            }

        //Valida que el valor del campo username no exista en la base de datos
        //Valida que el valor del campo password coincida con el password del user
        //Valida el maximo de usuarios conectados
        } catch (IncorrectUserException | IncorrectPasswordException | 
                UserDontExistException | PasswordDontMatchException | 
                TooManyUsersException | ConnectException | IOException ex) {

            logger.info("El cliente ha recibido un mensaje de error del servidor");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(ex.getMessage());
            alert.show();

        }

    }
    
    /**
     * Metodo tipo booleano que comprueba si los campos estan informados o no
     *
     * @return Devuelve una variable booleana que dice hay algun campo vacio (si 
     * es false hay un campo vacio, si es true esta todo informado)
     */
    private boolean informedFields() {

        logger.info("Se esta comprobando si algun campo esta vacio");
        
        if (txtUsername.getText().trim().isEmpty()) {
            logger.info("El campo usuario esta vacio y se va a enviar un mensaje "
                    + "al usuario");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce un nombre de usuario");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (txtPassword.getText().trim().isEmpty()) {
            logger.info("El campo password esta vacio y se va a enviar un "
                    + "mensaje al usuario");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce una contraseña");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else {
            logger.info("Todos los campos estan informados");
            
            return true;
        }

    }
    
    /**
     * Metodo tipo booleano que comprueba si los campos contienen mas de 255
     * caracteres
     *
     * @return Devuelve una variable booleana que dice hay algun campo que supera 
     * los 50 caracteres (si es false hay un campo que los supera, si es true 
     * ningun campo supera los 50 caracteres, por tanto, todo correcto)
     */
    private boolean maxCharacteres() {

        logger.info("Se esta comprobando si la cantidad de caracteres supera de "
                + "los campos supera los 50");
        
        if (txtUsername.getText().trim().length() >= 50) {
            logger.info("El campo usuario supera los 50 caracteres y se enviara "
                    + "un mensaje al usuario");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("El usuario ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else if (txtPassword.getText().trim().length() >= 50) {
            logger.info("El campo password supera los 50 caracteres y se enviara "
                    + "un mensaje al usuario");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("La contraseña ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else {
            logger.info("Todos los campos estan dentro del rango de caracteres");
            
            return true;
        }

    }
    
    /**
     * Metodo que se va a ejecutar una vez pulsado el hyperlink de signUp el
     * cual te va a llevar a la ventana SignUp
     *
     * @param event Respresenta la accion del evento handleSignUp
     * @throws IOException Excepcion requerida para el load()
     */
    @FXML
    private void handleSignUpAction(ActionEvent event) throws IOException {
        
        logger.info("Se ha pulsado el hyperlink para ir a la ventana de registro");
        
        //La ventana actual se cierra
        stage.close();

        //El usuario es dirigido a la ventana SignUp
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignUp.fxml"));

        Parent root = loader.load();

        VSignUpController controller = ((VSignUpController) loader.getController());
        controller.setStage(stage);
        controller.initStage(root);

    }
    
    /**
     * Metodo que se ejecutara una vez pulsado el boton exit el cual va a
     * confirmar si de verdad quiere salir
     *
     * @param event Respresenta la accion del evento handleExit
     */
    @FXML
    private void handleExitAction(ActionEvent event) {

        logger.info("Se ha pulsado el boton EXIT y se enviara un aviso de "
                + "confirmacion al usuario");
        
        //Se envia un aviso al usuario confirmando si de verdad quiere salir
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Seguro que quiere salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y el programa va a finalizar");
            
            //El programa finalizara
            stage.close();
        }else {
            logger.info("Se ha pulsado CANCELAR y el evento va a ser cancelado");
            
            //El aviso se cierra y el usuario continua en la ventana
            event.consume();
        }

    }

    /**
     * Metodo que se va a ejecutar una vez pulsado el boton X de la barra
     * de titulo de la aplicacion el cual va a confirmar si de verdad quiere
     * salir
     *
     * @param event Respresenta la accion del evento handleCloseRequest
     */
    @FXML
    private void handleCloseRequest(WindowEvent event) {

        logger.info("Se ha pulsado la X de la barra de titulo y se enviara "
                + "un aviso de confirmacion al usuario");
        
        //Se envia un mensaje al usuario confirmando si de verdad quiere salir
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Seguro que quiere salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            logger.info("Se ha pulsado OK y el programa va a finalizar");
            
            //El programa finalizara
            stage.close();
        } else {
            logger.info("Se ha pulsado CANCELAR y el evento va a ser cancelado");
            
            //El aviso se cierra y el usuario continua en la ventana
            event.consume();
        }

    }

}
