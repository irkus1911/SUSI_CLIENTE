/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.message.Message;
import lib.exceptions.ConnectException;
import lib.exceptions.TooManyUsersException;

/**
 *Esta clase implementa la Interfaz
 * @author Adrian Franco
 */
public class ClientSocket {

    private final static Logger logger = Logger.getLogger("client.socket.ClientSocket");
    private ResourceBundle configFile;
    private String SERVER;
    private int PORT;

    /**
     *
     * @param msg Objeto mensaje que se envia al servidor
     * @return Objeto Message recibido de Server con los usuarios,
     * datos y excepciones que hayan sido utlizadas
     * @throws ConnectException Excepcion que salta al conectarse con la base 
     * de datos
     * @throws TooManyUsersException excepcion para cuando el Usuario se pase
     * del limite de usuarios
     * @throws IOException Excepcion por la cual sacamos el Error de conexion
     */
    public Message createSocket(Message msg) throws ConnectException, TooManyUsersException, IOException {

        readFile();
        Message mg = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;

        try {
            socket = new Socket(SERVER, PORT);
            logger.severe("Conectado con el servidor");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(msg);
            logger.severe("Se ha enviado un objeto al servidor");
            mg = (Message) ois.readObject();
            logger.severe("Leyendo el objeto devuelto por el servidor");
            ois.close();
            oos.close();
            socket.close();
            logger.severe("Se ha cerrado el socket del cliente");
        } catch (IOException ex) {
            logger.severe("Error de conexion");
            throw new ConnectException("Error de conexion, intentalo mas tarde");          
        } catch (ClassNotFoundException ex) {
            logger.severe("");
        }
        return mg;
    }

    /**
     *
     */
    public void readFile() {
        this.configFile = ResourceBundle.getBundle("lib.message.Properties");
        
        this.SERVER = this.configFile.getString("SERVER");
        this.PORT = Integer.parseInt(this.configFile.getString("PORT"));
    }

}
