/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import client.Client;
import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
public class VSignUpControllerIT extends ApplicationTest{
    
    public VSignUpControllerIT() {
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
     * Primer método test el cual va a comprobar si el hyperlink hacia la ventana
     * SignUp funciona correctamente
     */
    @Test
    public void testA_OpenSignUp(){
        clickOn("#hlSignUp");
        verifyThat("#signUpPane", isVisible());
                        
    }
    
    /**
     * Este test comprueba si al dejar campos vacíos, el usuario es avisado y
     * no deja que se registre
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testB_FieldsEmpty() throws InterruptedException{
        
        clickOn("#buttonSignUp");
        verifyThat("Introduce el nombre de usuario", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Introduce el email", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#buttonSignUp");
        verifyThat("Introduce el nombre completo", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldFullName");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Introduce una contraseña", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Introduce una contraseña", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
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
    @Ignore
    public void testC_MaxCharacterValidation() throws InterruptedException {

        clickOn("#fieldUsername");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("El nombre de usuario ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);

        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("El email ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);

        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("El nombre completo ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);

        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("La contraseña ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("Vamos a escribir cincuenta caracteres para la prueba");
        clickOn("#buttonSignUp");
        verifyThat("La contraseña ha superado los 50 caracteres", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba si el usuario es avisado al introducir un patrón
     * incorrecto de Email
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testD_EmailPatron() throws InterruptedException{
                
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Campo email inválido", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba el patrón de los campos password y username
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testE_userPasswordPattern() throws InterruptedException{
        
        clickOn("#fieldUsername");
        write("*example€");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("a");
        clickOn("#fieldConfirmPassword");
        write("a");
        clickOn("#buttonSignUp");
        verifyThat("Campo username inválido", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldPassword");
        write("[abcd*1234]");
        clickOn("#fieldConfirmPassword");
        write("[abcd*1234]");
        clickOn("#buttonSignUp");
        verifyThat("Campo password inválido", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba si se envia correctamente una excepción al intentar
     * registrar un usuario que ya existe
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testF_UserAlreadyExist() throws InterruptedException{
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("El usuario ya existe", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
    }
    
    /**
     * Este test comprueba si el usuario es avisado al introducir valores 
     * distintos en los campos password y confirmpassword
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testG_PasswordMatchValidation() throws InterruptedException{
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("NotTheSameExample");
        clickOn("#buttonSignUp");
        verifyThat("La contraseña no coincide", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
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
    public void testH_ConnectionError() throws InterruptedException{
        
        clickOn("#fieldUsername");
        write("example");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Error de conextion, intentalo mas tarde", isVisible());
        Thread.sleep(1500);
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        
    }
    /**
     * Este test valida que salta la excepcion del correo ya registrado
     * @throws InterruptedException 
     */
    @Test
    public void testI_EmailExist() throws InterruptedException{
        clickOn("#fieldUsername");
        write("qwerty");
        clickOn("#fieldEmail");
        write("example@gmail.com");
        clickOn("#fieldFullName");
        write("example");
        clickOn("#fieldPassword");
        write("example");
        clickOn("#fieldConfirmPassword");
        write("example");
        clickOn("#buttonSignUp");
        verifyThat("Email ya existe", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        
        clickOn("#fieldUsername");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldEmail");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldFullName");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
        clickOn("#fieldConfirmPassword");
        push(KeyCode.CONTROL, KeyCode.A);
        eraseText(1);
    }
    
    /**
     * Este test comprueba si la ventana SignUp registra correctamente a un 
     * nuevo usuario
     * @throws InterruptedException Esta excepción saltará cuando el Thread no
     * puede hacer correctamente la acción de dormir
     */
    @Test
    @Ignore
    public void testJ_SignUp() throws InterruptedException{
        
        clickOn("#fieldUsername");
        write("Ronaldo");
        clickOn("#fieldEmail");
        write("ronaldinho@gmail.com");
        clickOn("#fieldFullName");
        write("Ronaldo Steven Arce");
        clickOn("#fieldPassword");
        write("abcd*1234");
        clickOn("#fieldConfirmPassword");
        write("abcd*1234");
        clickOn("#buttonSignUp");
        verifyThat("Usuario registrado correctamente", isVisible());
        Thread.sleep(1500);
        clickOn("Aceptar");
        verifyThat("#logOutPane", isVisible());
        Thread.sleep(1500);
        
    }
    
}
