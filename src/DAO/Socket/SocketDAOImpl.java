package DAO.Socket;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import MVC.Model.Socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocketDAOImpl implements SocketDAO {
    @Override
    public ArrayList<Socket> getAllSockets() {
        String querry = "SELECT * FROM socket";
        ArrayList<Socket> sockets = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(querry);
            // setString du nom pour la recherche
            ResultSet data = statement.executeQuery();
            while(data.next()){
                String name = data.getString("name");

                Socket socket = new Socket(name);
                sockets.add(socket);
            }
        }catch(SQLException e){
            throw new AllComponentsException("sockets");
        }
        return sockets;
    }
}
