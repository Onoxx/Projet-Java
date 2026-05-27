package DAO.MotherBoard;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.MotherBoard;
import MVC.Model.MotherBoardRamBrand;

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

    @Override
    public ArrayList<MotherBoardRamBrand> searchByFormatRamMaxPrice(String format, String ramName, double maxPrice) {
        String querry = "SELECT mb.name AS motherBoardName, mb.model, mb.nbRamSlots, r.type as ramType, r.capacity AS ramCapacity, r.frequency, b.name as brandName, b.website, b.country FROM motherboard mb JOIN ram r ON mb.ramType = r.type JOIN brand b ON mb.brand = b.name WHERE mb.format = ? AND r.name = ? AND mb.price <= ?";
        ArrayList<MotherBoardRamBrand> motherBoardRamBrands = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1,format);
            preparedStatement.setString(2,ramName);
            preparedStatement.setDouble(3,maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String motherBoardName = resultSet.getString("motherBoardName");
                String motherBoardmodel = resultSet.getString("model");
                int nbRamSlots = resultSet.getInt("nbRamSlots");
                String ramType = resultSet.getString("ramType");
                int ramCapacity = resultSet.getInt("ramCapacity");
                double frequency = resultSet.getDouble("frequency");
                String brandName = resultSet.getString("brandName");
                String website = resultSet.getString("website") ;
                String country = resultSet.getString("country");

                MotherBoardRamBrand motherBoardRamBrand = new MotherBoardRamBrand(
                        motherBoardName,
                        motherBoardmodel,
                        nbRamSlots,
                        ramType,
                        ramCapacity,
                        frequency,
                        brandName,
                        website,
                        country
                        );
                motherBoardRamBrands.add(motherBoardRamBrand);
            }
        } catch (SQLException ex) {
            throw new FailedToGetComponentException("carte mère");
        }
        return motherBoardRamBrands;
    }
}
