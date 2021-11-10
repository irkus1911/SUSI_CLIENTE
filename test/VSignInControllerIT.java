/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import client.Client;

import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Clase de test que extiende en la clase ApplicationTest el cual está ordenado
 * por nombre ascendiente (En nuestro caso alfabéticamente)
 * @author Adrian Franco, Unai Urtiaga, Irkus de la Fuente
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VSignInControllerIT extends ApplicationTest {

    public VSignInControllerIT() {

    }

    /**
     * Este método sirve para generar la ventana principal de la aplicación y sólo
     * se va a ejecutar una vez al iniciar el test
     * @throws TimeoutException Esta excepción salta si el método no consigue
     * generar la ventana
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Client.class);

    }
    
    /**
     * Este test comprueba si al dejar campos vacíos, el usuario es avisado y
     * no deja que se registre
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testA_InformedFieldValidation() throws InterruptedException {
        
        clickOn("#btnSignIn");
        verifyThat("Introduce un nombre de usuario", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");

        clickOn("#txtUsername");
        write("example");
        clickOn("#btnSignIn");
        verifyThat("Introduce una contraseña", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);

        clickOn("#txtPassword");
        write("example");
        clickOn("#btnSignIn");
        verifyThat("Introduce un nombre de usuario", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
       
    }
    /**
     * Este test comprueba si el usuario es avisado al introducir una cantidad
     * de caracteres superior a la permitida
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    
    @Test
    public void testB_MaxCharacterValidation() throws InterruptedException{
                
        clickOn("#txtUsername");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#txtPassword");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("El usuario ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
        clickOn("#txtPassword");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#txtUsername");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("La contraseña ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba la excepción de cuando no ha sido posible conectar con
     * la base de datos
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Ignore
    @Test
    public void testC_ConnectionException() throws InterruptedException{
        
        clickOn("#txtUsername");
        write("a");
        clickOn("#txtPassword");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("Error de conexion, intentalo mas tarde", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
    }
    
    /**
     * Este test comprueba si se envia correctamente una excepción al intentar
     * iniciar sesión con un usuario que no existe
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testD_UserDontExist() throws InterruptedException {
        clickOn("#txtUsername");
        write("a");
        clickOn("#txtPassword");
        write("example");
        clickOn("#btnSignIn");
        verifyThat("El usuario no existe", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);

    }
    
    /**
     * Este test comprueba si se ejecuta correctamente la excepción al introducir
     * un password que no coincida con el password del usuario en la base de datos
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testE_PasswordDontMatch() throws InterruptedException {
        clickOn("#txtUsername");
        write("example");
        clickOn("#txtPassword");
        write("a");
        clickOn("#btnSignIn");
        verifyThat("La contraseña no coincide", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#txtPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#txtUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba si la ventana SignIn inicia sesión correctamente a un
     * usuario existente
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testN_OpenLogOut() throws InterruptedException{
        clickOn("#txtUsername");
        write("example");
        clickOn("#txtPassword");
        write("example");
        clickOn("#btnSignIn");
        verifyThat("#logOutPane", isVisible());
        Thread.sleep(1500);
    }
    
    
}
