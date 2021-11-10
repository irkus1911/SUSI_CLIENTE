
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

        try {
            //Enviar el mensaje al servidor
            msg = socket.createSocket(msg);
        } catch (IOException ex) {
            //Error de conexion con el servidor
            throw new ConnectException("Error de conexion, intentalo mas tarde");
        }
        //Traducir el mensaje de vuelta
        if (msg.getMsg() == Msg.USERDONTEXISTEXCEPTION) {
            ///Usuario no existe
            throw new UserDontExistException("El usuario no existe");
        } else if (msg.getMsg() == Msg.CONNECTEXCEPTION) {
            //Error de conexion
            throw new ConnectException("Error al conectarse con la base de datos");
        } else if (msg.getMsg() == PASSWORDDONTMATCHEXCEPTION) {
            //Contraseña no es la del usuario
            throw new PasswordDontMatchException("La contraseña no coincide");
        } else if (msg.getMsg() == Msg.TOOMANYUSERSEXCEPTION) {
            //Error demasiadas conexiones
            throw new TooManyUsersException("Servidor lleno, intentalo mas tarde");
        }
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

        try {
            //Enviar el mensaje al servidor
            msg = socket.createSocket(msg);
        } catch (IOException ex) {
            //Error conexion con el servidor
            throw new ConnectException("Error de conexion, intentalo mas tarde");
        }
        //Traducir el mensaje de vuelta
        if (msg.getMsg() == Msg.USEREXISTEXCEPTION) {
            //Usuario ya existe
            throw new UserExistException("El usuario ya existe");
        } else if (msg.getMsg() == Msg.CONNECTEXCEPTION) {
            //Error de conexion
            throw new ConnectException("Error al conectarse con la base de datos");
        } else if (msg.getMsg() == Msg.PASSWORDDONTMATCHEXCEPTION) {
            //Error contraseñas no coinciden
            throw new PasswordDontMatchException("La contraseña no coincide");
        } else if (msg.getMsg() == Msg.TOOMANYUSERSEXCEPTION) {
            //Error superado el limite de conexiones
            throw new TooManyUsersException("Servidor lleno, intentalo mas tarde");
        }
        //Devolver el usuario
        return msg.getUser();
    }
}
