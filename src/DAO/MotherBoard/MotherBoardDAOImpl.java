package DAO.MotherBoard;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.MotherBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotherBoardDAOImpl implements MotherBoardDAO {
    @Override
    public ArrayList<MotherBoard> getAllMotherBoards() {
        String querry = "SELECT * FROM motherboard";
        ArrayList<MotherBoard> motherBoards = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String socket = resultSet.getString("socket");
                String format = resultSet.getString("format");
                String ramType = resultSet.getString("ramType");
                int nbRamSlots = resultSet.getInt("nbRamSlots");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");

                MotherBoard motherBoard = new MotherBoard(name, model, socket, format, ramType,brand, nbRamSlots, price);
                motherBoards.add(motherBoard);
            }
        }catch(SQLException e){
            throw new AllComponentsException("cartes mères");
        }
        return motherBoards;
    }
    @Override
    public MotherBoard getMotherBoardByName(String motherBoardName) {
        String querry = "SELECT * FROM motherboard WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, motherBoardName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String socket = resultSet.getString("socket");
                String format = resultSet.getString("format");
                String ramType = resultSet.getString("ramType");
                int nbRamSlots = resultSet.getInt("nbRamSlots");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");

                return new MotherBoard(name, model, socket, format, ramType,brand, nbRamSlots, price);
            }
        }catch(SQLException ex){
            throw new FailedToGetComponentException("carte mère");
        }
        return null;
    }
}
