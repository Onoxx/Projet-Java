package BusinessLogic;

import DAO.SocketDAOImpl;
import MVC.Model.Socket;

import java.util.ArrayList;

public class SocketManager {
    private SocketDAOImpl socketDAO;
    public SocketManager() {
        this.socketDAO = new SocketDAOImpl();
    }

    public ArrayList<Socket> getAllSockets() {
        ArrayList<Socket> socketsList = socketDAO.getAllSockets();
        return socketsList;
    }
}
