package DAO.Configuration;

import DAO.SingletonConnection;
import Exceptions.FailedToAddComponentException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.*;

import java.sql.*;
import java.util.ArrayList;

public class ConfigurationDAOImpl implements ConfigurationDAO {
    @Override
    public int addConfiguration(Configuration conf) {
        String querryAddConfig =
                "INSERT INTO configuration " +
                        "(user, processor, graphicCard, computerCase, motherBoard, ram, creationDate) " +
                        "VALUES (?,?,?,?,?,?,?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(
                    querryAddConfig,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1,conf.getUser().getId());
            ps.setString(2,
                    conf.getProcessor() != null
                            ? conf.getProcessor().getName()
                            : null);

            ps.setString(3,
                    conf.getGraphicCard() != null
                            ? conf.getGraphicCard().getName()
                            : null);

            ps.setString(4,
                    conf.getComputerCase() != null
                            ? conf.getComputerCase().getName()
                            : null);

            ps.setString(5,
                    conf.getMotherBoard() != null
                            ? conf.getMotherBoard().getName()
                            : null);

            ps.setString(6,
                    conf.getRam() != null
                            ? conf.getRam().getName()
                            : null);

            ps.setDate(7, new Date(conf.getDate().getTime()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            return rs.next() ? rs.getInt(1) : -1;
        }catch(SQLException e){
            throw new FailedToAddComponentException("configuration");
        }
    }
    @Override
    public void addStorageConfiguration(StorageConfiguration conf) {
        String addStorageQuerry = "INSERT INTO storageConfiguration (quantity, storage, configuration) VALUES (?, ?, ?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(addStorageQuerry);
            ps.setInt(1,conf.getQuantity());
            ps.setString(2,conf.getStorage());
            ps.setInt(3,conf.getConfiguration());

            ps.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToAddComponentException("configuration");
        }
    }
    @Override
    public void addCoolingConfiguration(CoolingConfiguration conf) {
        String addCoolingQuerry = "INSERT INTO coolingConfiguration (quantity, cooling, configuration) VALUES (?, ?, ?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(addCoolingQuerry);
            ps.setInt(1,conf.getQuantity());
            ps.setString(2,conf.getCooling());
            ps.setInt(3,conf.getConfiguration());

            ps.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToAddComponentException("configuration");
        }
    }

    @Override
    public ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB) {
        String querry = "SELECT c.id AS configurationId, p.name AS processorName, p.nbCores, p.baseFrequence, p.boostFrequence, g.name AS graphicCardName, g.chipset, g.capacity AS vRamCapacity, g.vRamType, r.name AS ramName, r.capacity, r.nbRamSticks, r.type AS ramType FROM configuration c JOIN processor p ON c.processor = p.name JOIN graphicCard g ON c.graphicCard = g.name JOIN ram r ON c.ram = r.name JOIN user u ON c.user = u.id JOIN computercase cc ON c.computerCase = cc.name WHERE u.name = ? AND c.creationDate <= DATE_SUB(NOW(), INTERVAL ? MONTH) AND cc.hasRGB = ?";
        ArrayList<ConfigurationSearch> configurationSearches = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, age);
            preparedStatement.setBoolean(3, hasRGB);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int configurationId = resultSet.getInt("configurationId");

                String processorName = resultSet.getString("processorName");
                int nbCores = resultSet.getInt("nbCores");
                double baseFrequence = resultSet.getDouble("baseFrequence");
                double boostFrequence = resultSet.getDouble("boostFrequence");

                String graphicCardName = resultSet.getString("graphicCardName");
                String chipset = resultSet.getString("chipset");
                int vRamCapacity = resultSet.getInt("vRamCapacity");
                String vRamType = resultSet.getString("vRamType");

                String ramName = resultSet.getString("ramName");
                int capacity = resultSet.getInt("capacity");
                int nbRamSticks = resultSet.getInt("nbRamSticks");
                String ramType = resultSet.getString("ramType");

                ConfigurationSearch configurationSearch = new ConfigurationSearch(
                        configurationId,
                        processorName,
                        nbCores,
                        baseFrequence,
                        boostFrequence,
                        graphicCardName,
                        chipset,
                        vRamCapacity,
                        vRamType,
                        ramName,
                        capacity,
                        nbRamSticks,
                        ramType
                );
                configurationSearches.add(configurationSearch);
            }
        } catch (SQLException ex) {
            throw new FailedToGetComponentException("configuration");
        }
        return configurationSearches;
    }
}
