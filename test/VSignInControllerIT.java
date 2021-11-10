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
    
    
    
}
