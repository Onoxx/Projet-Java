package DAO.Processor;

import DAO.SingletonConnection;
import Exceptions.*;
import MVC.Model.Processor;

import java.sql.*;
import java.util.ArrayList;

public class ProcessorDAOImpl implements ProcessorDAO {
    @Override
    public ArrayList<Processor> getAllProcessors() {
        String querry = "SELECT * FROM processor";
        ArrayList<Processor> processors = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(querry);
            ResultSet data = statement.executeQuery();
            while(data.next()){
                String name = data.getString("name");
                String socket = data.getString("socket");
                String brand = data.getString("brand");
                int nbCores = data.getInt("nbCores");
                Integer nbThreads = data.getObject("nbThreads", Integer.class);
                double baseFrequence = data.getDouble("baseFrequence");
                Double boostFrequence = data.getObject("boostFrequence", Double.class);
                int tdp = data.getInt("tdp");
                boolean hasGPU = data.getBoolean("hasGPU");
                double price = data.getDouble("price");
                Date releaseDate = data.getDate("releaseDate");

                Processor processor = new Processor(name,socket, brand, nbCores, nbThreads,
                        tdp, baseFrequence, boostFrequence, price, hasGPU, releaseDate);
                processors.add(processor);
            }
        }catch(SQLException e){
            throw new AllComponentsException("processeurs");
        }
        return processors;
    }
    public Processor getProcessorByName(String processorName) {
        String querry = "SELECT * FROM processor WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setString(1, processorName);
            ResultSet data = statement.executeQuery();
            if(data.next()){
                String name = data.getString("name");
                String socket = data.getString("socket");
                String brand = data.getString("brand");
                int nbCores = data.getInt("nbCores");
                Integer nbThreads = data.getObject("nbThreads", Integer.class);
                double baseFrequence = data.getDouble("baseFrequence");
                Double boostFrequence = data.getObject("boostFrequence", Double.class);
                int tdp = data.getInt("tdp");
                boolean hasGPU = data.getBoolean("hasGPU");
                double price = data.getDouble("price");
                Date releaseDate = data.getDate("releaseDate");

                 Processor processor = new Processor(name,socket, brand, nbCores, nbThreads,
                        tdp, baseFrequence, boostFrequence, price, hasGPU, releaseDate);
                return processor;
            }
        }catch(SQLException ex){
            throw new FailedToGetComponentException("processeur");
        }
        return null;
    }
    @Override
    public void updateProcessor(String name, Processor processor) {
        Connection connection = SingletonConnection.getInstance();
        if(isProcessorExists(name) && (!isProcessorExists(processor.getName()) || name.equals(processor.getName()))){
            try{
                String updateQuerry = "UPDATE processor SET name = ?, socket = ?, nbCores = ?, nbThreads = ?, baseFrequence = ?, boostFrequence = ?, " +
                        "tdp = ?, hasGPU = ?, price = ?, releaseDate = ?, brand = ? WHERE name = ?";
                PreparedStatement ps = connection.prepareStatement(updateQuerry);
                ps.setString(1, processor.getName());
                ps.setString(2, processor.getSocket());
                ps.setInt(3, processor.getNbCores());
                if(processor.getNbThreads() != null){
                    ps.setInt(4, processor.getNbThreads());
                }
                else{
                    ps.setNull(4, java.sql.Types.INTEGER);
                }
                ps.setDouble(5, processor.getBaseFrequence());
                if(processor.getBoostFrequence() != null){
                    ps.setDouble(6, processor.getBoostFrequence());
                }
                else{
                    ps.setNull(6, java.sql.Types.DOUBLE);
                }
                ps.setInt(7, processor.getTdp());
                ps.setBoolean(8, processor.isHasGPU());
                ps.setDouble(9, processor.getPrice());
                ps.setDate(10, new java.sql.Date(processor.getReleaseDate().getTime()));
                ps.setString(11, processor.getBrand());
                ps.setString(12, name);
                ps.executeUpdate();
            }catch(SQLException ex){
                throw new FailedToUpdateComponentException("processeur");
            }
        }
    }
    @Override
    public void addProcessor(Processor processor) {
        String querry = "INSERT INTO processor " +
                "(name, socket, nbCores, nbThreads, baseFrequence, boostFrequence, tdp, hasGPU, price, releaseDate, brand) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = SingletonConnection.getInstance();
        if(!isProcessorExists(processor.getName())){
            try{
                PreparedStatement ps = connection.prepareStatement(querry);
                ps.setString(1, processor.getName());
                ps.setString(2, processor.getSocket());
                ps.setInt(3, processor.getNbCores());
                if(processor.getNbThreads() != null){
                    ps.setInt(4, processor.getNbThreads());
                }
                else{
                    ps.setNull(4, java.sql.Types.INTEGER);
                }
                ps.setDouble(5, processor.getBaseFrequence());
                if(processor.getBoostFrequence() != null){
                    ps.setDouble(6, processor.getBoostFrequence());
                }
                else{
                    ps.setNull(6, java.sql.Types.DOUBLE);
                }
                ps.setInt(7, processor.getTdp());
                ps.setBoolean(8, processor.isHasGPU());
                ps.setDouble(9, processor.getPrice());
                ps.setDate(10, new Date(processor.getReleaseDate().getTime()));
                ps.setString(11, processor.getBrand());
                ps.executeUpdate();
            }catch(SQLException ex){
                throw new FailedToAddComponentException("processeur");
            }
        }
    }
    @Override
    public void removeProcessor(String name) {
        String querry = "DELETE FROM processor WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
           PreparedStatement preparedStatement = connection.prepareStatement(querry);
           preparedStatement.setString(1,name);

           preparedStatement.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToRemoveComponentException("processeur");
        }
    }
    private boolean isProcessorExists(String name) {
        String querry = "SELECT * FROM processor WHERE name = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(querry);
            ps.setString(1, name);
            ResultSet data = ps.executeQuery();
            return data.next();
        }catch(SQLException ex){
            throw new FailedToAddComponentException("processeur");
        }
    }
}
