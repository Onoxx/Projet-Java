package DAO;

import Exceptions.AllComponentsException;
import Exceptions.FailedToAddProcessorException;
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
            // setString du nom pour la recherche
            ResultSet data = statement.executeQuery();
            while(data.next()){
                String name = data.getString("name");
                String socket = data.getString("socket");
                String brand = data.getString("brand");
                int nbCores = data.getInt("nbCores");
                int nbThreads = data.getInt("nbThreads");
                double baseFrequence = data.getDouble("baseFrequence");
                double boostFrequence = data.getDouble("boostFrequence");
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

    @Override
    public void updateProcessor(String name, Processor processor) {

    }

    @Override
    public void addProcessor(Processor processor) {
        String querry = "INSERT INTO processor " +
                "(name, socket, nbCores, nbThreads, baseFrequence, boostFrequence, tdp, hasGPU, price, releaseDate, brand) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = SingletonConnection.getInstance();
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
            ps.setDate(10, new java.sql.Date(processor.getReleaseDate().getTime()));
            ps.setString(11, processor.getBrand());
            ps.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToAddProcessorException();
        }
    }

    @Override
    public void removeProcessor(String name) {

    }
}
