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
 * Clase de test que extiende en la clase ApplicationTest el cual prueba la 
 * ventana de LogOut y está ordenado por nombre ascendiente (En nuestro caso 
 * alfabéticamente).
 * @author Steven Arce
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VLogOutControllerIT extends ApplicationTest {

    public VLogOutControllerIT() {

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
     * Este test comprueba si la ventana SignIn inicia sesión correctamente a un
     * usuario existente.
     * @throws InterruptedException esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testA_OpenLogOut() throws InterruptedException{
        clickOn("#txtUsername");
        write("example");
        clickOn("#txtPassword");
        write("example");
        clickOn("#btnSignIn");
        verifyThat("#logOutPane", isVisible());
        Thread.sleep(3500);
    }
    /**
     * Este test pretende probar que cuando se pulse al boton "LogOut", se pueda
     * cancelar la accion.
     * @throws InterruptedException esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    public void testB_LogOutButtonCancel() throws InterruptedException {
        clickOn("#btnLogOut");
        clickOn("Cancelar");
        verifyThat("#logOutPane", isVisible());
        Thread.sleep(2000);
    }
    /**
     * Este test pretende probar que cuando se pulse al boton "LogOut", y es 
     * aceptada la accion, se abra la ventana de SignIn.
     */
    @Test
    public void testC_LogOutButtonAccept() {
        clickOn("#btnLogOut");
        clickOn("Aceptar");
        verifyThat("#signInPane", isVisible());
    }
    
}
