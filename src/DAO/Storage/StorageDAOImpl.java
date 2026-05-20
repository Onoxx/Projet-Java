package DAO.Storage;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import MVC.Model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageDAOImpl implements StorageDAO {
    @Override
    public ArrayList<Storage> getAllStorages(){
        String querry = "SELECT * FROM storage";
        ArrayList<Storage> storages = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String interfaceStorage = resultSet.getString("interface");
                int capacity = resultSet.getInt("capacity");
                int writingSpeed = resultSet.getInt("writingSpeed");
                int readingSpeed = resultSet.getInt("readingSpeed");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");

                Storage storage = new Storage(name, type, interfaceStorage, brand, capacity, writingSpeed, readingSpeed,price);
                storages.add(storage);
            }
        } catch (SQLException e) {
            throw new AllComponentsException("stockages");
        }
        return storages;
    }
}
