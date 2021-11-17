package client.controllers;

import client.controllers.VLogOutController;
import client.controllers.VSignInController;
import client.factory.LogicableFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Observable;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.KeyEvent;
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
 *
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
     *
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

        //Limitar la entrada de maximo 50 caracteres (ChangeListener)
        fieldUsername.textProperty().addListener(this::limitCharacters);
        fieldEmail.textProperty().addListener(this::limitCharacters);
        fieldFullName.textProperty().addListener(this::limitCharacters);
        fieldPassword.textProperty().addListener(this::limitCharacters);
        fieldConfirmPassword.textProperty().addListener(this::limitCharacters);

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
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void limitCharacters(ObservableValue<? extends String> observable, String oldValue,
            String newValue) {
        logger.info("Inicio del metodo para limitar la entrada de un maximo de caracteres");
        
        if (fieldUsername.getText().length() > 50) {
            fieldUsername.setText(oldValue);
        }
        if (fieldEmail.getText().length() > 50) {
            fieldEmail.setText(oldValue);
        }
        if (fieldFullName.getText().length() > 50) {
            fieldFullName.setText(oldValue);
        }
        if (fieldPassword.getText().length() > 50) {
            fieldPassword.setText(oldValue);
        }
        if (fieldConfirmPassword.getText().length() > 50) {
            fieldConfirmPassword.setText(oldValue);
        }

    }

    /**
     * Este metodo pretende validar todos los campos, si todo es valido mandara
     * un mensaje al servidor diciendo que hara un signUp. Si todo va bien,
     * aparecera una alerta diciendo que todo ha salido bien. En caso de haber
     * algun error saldra una alerta indicando el tipo de error.
     *
     * @param event representa la accion del evento handleSignUp
     */
    private void handleSignUp(ActionEvent event) {
        logger.info("Se ha pulsado el boton SignUp");
        logger.info("Se ha iniciado el metodo para hacer signUp");

        if (informedFields() && maxCharacteres() && userCharacterLimitation()
                && confirmPassword() && emailPattern()) {

            User user = new User();
            user.setLogin(fieldUsername.getText());
            user.setEmail(fieldEmail.getText());
            user.setFullName(fieldFullName.getText());
            user.setPassword(fieldPassword.getText());
            user.setPrivilege(UserPrivilege.USER);
            user.setStatus(UserStatus.ENABLED);
            user.setLastPasswordChange(Timestamp.from(Instant.now()));

            LogicableFactory logicableFactory = new LogicableFactory();
            try {
                logger.info("Llamando a la factoria para que el data traffic haga el signUp");
                if (logicableFactory.getDataTraffic().signUp(user) != null) {
                    logger.info("Usuario registrado correctamente");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Usuario registrado correctamente");
                    alert.showAndWait();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VLogOut.fxml"));
                    Parent root = loader.load();
                    VLogOutController controller = ((VLogOutController) loader.getController());
                    controller.setStage(stage);
                    controller.initStage(root);
                    logger.info("Se ha abierto la ventana de LogOut");
                }
                logger.info("Llamada finalizada");
            } catch (IncorrectUserException | IncorrectPasswordException
                    | IncorrectEmailException | PasswordDontMatchException
                    | TooManyUsersException | IOException | UserExistException
                    | ConnectException ex) {
                logger.info("El cliente ha recibido un mensaje de error del servidor");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(ex.getMessage());
                alert.show();
            }

        }

    }

    /**
     * Este metodo pretende controlar que cualquiera de los campos no pueda
     * sobrepasar los 50 caracteres.
     *
     * @return Devuelve el estado del campo, false si el campo sobrepasa la
     * longitud permitida, sino devuelve false con una alerta indicando el fallo
     */
    private boolean maxCharacteres() {
        logger.info("Iniciado el evento para comprobar la longitud del campo");

        if (fieldUsername.getText().trim().length() >= 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("El nombre de usuario ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else if (fieldEmail.getText().trim().length() >= 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("El email ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else if (fieldFullName.getText().trim().length() >= 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("El nombre completo ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else if (fieldPassword.getText().trim().length() >= 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("La contraseña ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else if (fieldConfirmPassword.getText().trim().length() >= 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("La contraseña ha superado los 50 caracteres");
            alert.setContentText("No se puede superar los 50 caracteres");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Este metodo pretende controlar que los campos usuario y contraseña, solo
     * se puede introducir letras, numeros, guiones bajos, puntos y asteriscos.
     *
     * @return Devuelve si los campos son validos, false si es invalido alguno
     * de los dos campos
     */
    private boolean userCharacterLimitation() {
        logger.info("Iniciado el evento para comprobar la validacion del usuario"
                + "y la contraseña");

        if (!fieldUsername.getText().matches("^[a-zA-Z0-9_*.]+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo username inválido");
            alert.setContentText("Campo username solo puede contener letras, "
                    + "numeros, guiones bajos, puntos y asteriscos");
            alert.show();
            return false;
        } else if (!fieldPassword.getText().matches("^[a-zA-Z0-9_*.]+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo password inválido");
            alert.setContentText("Campo password solo puede contener letras, "
                    + "numeros, guiones bajos, puntos y asteriscos");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Este metodo pretende controlar si los dos campos para escribir la
     * contraseña coinciden.
     *
     * @return Si las contraseñas coinciden devuelve true, si no devuelve false
     * con una alerta indicando el fallo
     */
    private boolean confirmPassword() {
        logger.info("Iniciado el evento para controlar las contraseñas");

        if (!fieldPassword.getText().equals(fieldConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("La contraseña no coincide");
            alert.setContentText("Inténtalo de nuevo");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Este metodo pretende controlar el patron que se puede introducir en el
     * campo email, si es invalido se mostrara una alerta.
     *
     * @return Devuelve el estado del email, false si es invalido y true si es
     * valido
     */
    private boolean emailPattern() {
        logger.info("Iniciado el evento para controlar si el campo email es valido");

        if (!fieldEmail.getText().matches("[\\w.]+@[\\w]+\\.[a-zA-Z]{2,4}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo email inválido");
            alert.setContentText("Campo email solo puede contener letras, números "
                    + "y puntos, además de un @ obligatorio");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Este metodo pretende controlar si algun campo de la ventana singUp esta
     * vacio mediante una alerta.
     *
     * @return Devuelve el estado del campo, false si esta vacio y true si esta
     * informado
     */
    private boolean informedFields() {
        logger.info("Iniciado el evento para controlar si el campo esta vacio");

        if (fieldUsername.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce el nombre de usuario");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (fieldEmail.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce el email");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (fieldFullName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce el nombre completo");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (fieldPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce una contraseña");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (fieldConfirmPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Introduce una contraseña");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Este metodo pretende regresar a la ventana singIn mediante la pulsacion
     * del boton back.
     *
     * @param event representa la accion del evento handleBack
     */
    private void handleBack(ActionEvent event) {
        logger.info("Se ha pulsado el boton back");
        logger.info("se volvera a la ventana de VSignIn");
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
     *
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
