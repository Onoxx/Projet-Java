package DAO.Ram;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import MVC.Model.Ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RamDAOImpl implements RamDAO {
    @Override
    public ArrayList<Ram> getAllRams() {
        String querry = "SELECT * FROM ram";
        ArrayList<Ram> rams = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String type = resultSet.getString("type");
                int frequency = resultSet.getInt("frequency");
                int nbRamSticks = resultSet.getInt("nbRamSticks");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");

                Ram ram = new Ram(name, type, brand, capacity, frequency, nbRamSticks, price);
                rams.add(ram);
            }
        }catch(SQLException e){
            throw new AllComponentsException("RAM");
        }
        return rams;
    }
    public Ram getRamByName(String ramName) {
        String querry = "SELECT * FROM ram WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setString(1, ramName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String type = resultSet.getString("type");
                int frequency = resultSet.getInt("frequency");
                int nbRamSticks = resultSet.getInt("nbRamSticks");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");
                return new Ram(name, type, brand, capacity, frequency, nbRamSticks, price);
            }
        }catch(SQLException ex){
            throw new AllComponentsException("RAM");
        }
        return null;
    }
}
