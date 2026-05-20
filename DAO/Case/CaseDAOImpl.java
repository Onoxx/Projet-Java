package DAO.Case;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import MVC.Model.Case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaseDAOImpl implements CaseDAO {
    @Override
    public ArrayList<Case> getAllCases(){
        String querry = "SELECT * FROM computercase";
        ArrayList<Case> cases = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String format = resultSet.getString("format");
                int maxLengthGPU = resultSet.getInt("maxLengthGPU");
                int maxHeightVentirad = resultSet.getInt("maxHeightVentirad");
                int nbFans = resultSet.getInt("nbFans");
                boolean hasRGB = resultSet.getBoolean("hasRGB");
                String brand = resultSet.getString("brand");

                Case currentCase = new Case(name, format, brand, price, maxLengthGPU, maxHeightVentirad, nbFans, hasRGB);
                cases.add(currentCase);
            }
        }catch (SQLException e){
            throw new AllComponentsException("boitiers");
        }
        return cases;
    }
    @Override
    public Case getCaseByName(String caseName) {
        String querry = "SELECT * FROM computercase WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setString(1, caseName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String format = resultSet.getString("format");
                int maxLengthGPU = resultSet.getInt("maxLengthGPU");
                int maxHeightVentirad = resultSet.getInt("maxHeightVentirad");
                int nbFans = resultSet.getInt("nbFans");
                boolean hasRGB = resultSet.getBoolean("hasRGB");
                String brand = resultSet.getString("brand");

                return new Case(name,format,brand,price, maxLengthGPU, maxHeightVentirad, nbFans, hasRGB);
            }

        }catch (SQLException ex){
            throw new AllComponentsException("boitiers");
        }
        return null;
    }
}
