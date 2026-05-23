package DAO.Cooling;

import DAO.SingletonConnection;
import MVC.Model.Cooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoolingDAOImpl implements CoolingDAO {
    @Override
    public ArrayList<Cooling> getAllCoolings() {
        ArrayList<Cooling> coolings = new ArrayList<>();
        String querry = "SELECT * FROM cooling";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String compatibleSocket = resultSet.getString("compatibleSocket");
                int length = resultSet.getInt("length");
                int height = resultSet.getInt("height");
                int weight = resultSet.getInt("weight");
                int depth = resultSet.getInt("depth");
                int tdp = resultSet.getInt("tdp");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");

                Cooling cooling = new Cooling(name, type, compatibleSocket, brand, length, height, weight, depth, tdp, price);
                coolings.add(cooling);
            }
        }catch(SQLException e){
            throw new RuntimeException("refroidissement");
        }
        return coolings;
    }
}
