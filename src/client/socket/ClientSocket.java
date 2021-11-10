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
import lib.dataModel.User;
import lib.message.Message;
import lib.message.Msg;

/**
 *Esta clase implementa la Interfaz
 * @author Adrian Franco
 * 
 */
public class ClientSocket {

    private ResourceBundle configFile;
    private String port;
    private int ip;
    private final static int PORT = 5001;
    private final static String SERVER = "127.0.0.1";

    /**
     * @param args Usuario recibido
     * @return Objeto Message recibido de Server con los usuarios,
     * datos y excepciones que hayan sido utlizadas
     */

    public Message createSocket(Message msg) {
    /**
     * 
     * Metodo en el que abrimos la conexion con el cliente y le pasamos la 
     * clase Message
     */
        Message mg = null;
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(SERVER, PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(msg);
            mg = (Message) ois.readObject();
            socket.close();
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        }
        return mg;

    }

}
