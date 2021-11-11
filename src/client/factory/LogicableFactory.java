package client.factory;

import client.socket.DataTraffic;
import lib.dataModel.User;
import lib.interfaces.Logicable;

/**
 * Esta clase es la factoria que crea un nuevo DataTraffic
 * @author UnaiUrtiaga
 */
public class LogicableFactory {
    
    /**
     * Metodo de la factoria del lado del cliente para implementar la clase 
     * DataTraffic
     * @return Devuelve un objeto de la interfaz Logicable el cual va a servir
     * para luego implementar la siguiente clase
     */
    
    public Logicable getDataTraffic(){
        
        Logicable dataTraffic = new DataTraffic();
        
        return dataTraffic;
    }
    
}