
package client.socket;

import java.io.IOException;
import lib.dataModel.User;
import lib.exceptions.ConnectException;
import lib.exceptions.IncorrectEmailException;
import lib.exceptions.IncorrectPasswordException;
import lib.exceptions.IncorrectUserException;
import lib.exceptions.PasswordDontMatchException;
import lib.exceptions.TooManyUsersException;
import lib.exceptions.UserDontExistException;
import lib.exceptions.UserExistException;
import lib.interfaces.Logicable;
import lib.message.Message;
import lib.message.Msg;
import static lib.message.Msg.PASSWORDDONTMATCHEXCEPTION;

/**
 * Esta clase convierte un usuario en un objeto mensaje es decir le 
 * añade al objeto usuario el metodo (signUp/signIn) y las excepciones o el ok en caso de no haber saltado ninguna excepcion
 * @author Irkus de la Fuente
 */
public class DataTraffic implements Logicable {

    /**
     *  Este metodo recibe un objeto usuario y lo convierte en el objeto mensaje  asignandole el signIn a realizar a parte tambien traduce el mensaje de vuelta del servidor lanzando las diferentes excepciones
     * @param user Objeto usuario recibido mediante el socket 
     * @return objeto User Devuelve el usuario en caso de no encontrar nada devuelve nulo
     * @throws IncorrectUserException El usuario no es alfanumerico
     * @throws IncorrectPasswordException La contraseña no es alfanumerica
     * @throws UserDontExistException El usuario no esta registrado en la base de datos
     * @throws PasswordDontMatchException La contraseña no esta registrada en la base de datos
     * @throws ConnectException Hay un error de conexion con la base de datos
     */
    //SignIn  Recibe Usuario/Devuelve Usuario
    @Override
    public User signIn(User user) throws IncorrectUserException, IncorrectPasswordException, UserDontExistException, PasswordDontMatchException, ConnectException, TooManyUsersException {
        //Crear socket cliente
        ClientSocket socket = new ClientSocket();
         //Asignarle mensaje(metodo+user)
        Message msg = new Message();
        msg.setUser(user);
        msg.setMsg(Msg.SIGNIN);

       
            //Enviar el mensaje al servidor
            msg = socket.createSocket(msg);
     
            //Error de conexion con el servidor
        
        //Traducir el mensaje de vuelta
       
            ///Usuario no existe
            
            //Error de conexion
       
            //Contraseña no es la del usuario
     
            //Error demasiadas conexiones
          
        //Devolver el usuario
        return msg.getUser();
    }

   /**
     * Este metodo recibe un objeto usuario y lo convierte en el objeto mensaje  asignandole el signUp a realizar a parte tambien traduce el mensaje de vuelta del servidor lanzando las diferentes excepciones
     * @param user Objeto usuario recibido mediante el socket 
     * @return objeto User Devuelve el usuario creado
     * @throws IncorrectUserException El usuario no es alfanumerico
     * @throws IncorrectPasswordException La contraseña no es alfanumerica
     * @throws IncorrectEmailException Patron de correo incorrecto 
     * @throws UserExistException   Usuario ya existe en la base de datos
     * @throws PasswordDontMatchException Las contraseñas no coinciden entre si(contraseña y confirmar contraseña)
     * @throws ConnectException Hay un error de conexion con la base de datos
     */
     //SignUp  Recibe Usuario/Devuelve Usuario
    @Override
    public User signUp(User user) throws IncorrectUserException, IncorrectPasswordException, IncorrectEmailException, UserExistException, PasswordDontMatchException, ConnectException, TooManyUsersException {
        //Crear socket cliente
        ClientSocket socket = new ClientSocket();
        //Asignarle mensaje(metodo+user)
        Message msg = new Message();
        msg.setUser(user);
        msg.setMsg(Msg.SIGNUP);

      
            //Enviar el mensaje al servidor
            msg = socket.createSocket(msg);
       
            //Error conexion con el servidor
     
        //Traducir el mensaje de vuelta
    
            //Usuario ya existe
        
            //Error de conexion
         
            //Error contraseñas no coinciden
        
            //Error superado el limite de conexiones
       
        //Devolver el usuario
        return msg.getUser();
    }
}
