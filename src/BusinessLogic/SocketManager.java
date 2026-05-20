package BusinessLogic;

import DAO.*;
import MVC.Model.Socket;

import java.util.ArrayList;

public class SocketManager {
    private SocketDAO socketDAO;
    public SocketManager() {
        this.socketDAO = new SocketDAOImpl();
    }

    public ArrayList<Socket> getAllSockets() {
        ArrayList<Socket> socketsList = socketDAO.getAllSockets();
        return socketsList;
    }
}
