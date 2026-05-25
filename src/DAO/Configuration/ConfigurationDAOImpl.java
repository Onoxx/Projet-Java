package DAO.Configuration;

import DAO.SingletonConnection;
import Exceptions.FailedToAddComponentException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.*;

import java.sql.*;
import java.util.HashMap;

public class ConfigurationDAOImpl implements ConfigurationDAO {
    @Override
    public void addConfiguration(Configuration config, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings){
        String querryAddConfig =
                "INSERT INTO configuration " +
                        "(user, processor, graphicCard, computerCase, motherBoard, ram, creationDate) " +
                        "VALUES (?,?,?,?,?,?,?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement psConfig = connection.prepareStatement(
                    querryAddConfig,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            psConfig.setInt(1,config.getUser().getId());
            psConfig.setString(2,
                    config.getProcessor() != null
                            ? config.getProcessor().getName()
                            : null);

            psConfig.setString(3,
                    config.getGraphicCard() != null
                            ? config.getGraphicCard().getName()
                            : null);

            psConfig.setString(4,
                    config.getComputerCase() != null
                            ? config.getComputerCase().getName()
                            : null);

            psConfig.setString(5,
                    config.getMotherBoard() != null
                            ? config.getMotherBoard().getName()
                            : null);

            psConfig.setString(6,
                    config.getRam() != null
                            ? config.getRam().getName()
                            : null);

            psConfig.setDate(7, new Date(config.getDate().getTime()));

            psConfig.executeUpdate();

            ResultSet rs = psConfig.getGeneratedKeys();
            int configurationId = -1;
            if (rs.next()) {
                configurationId = rs.getInt(1);
            }
            String addStorageQuerry = "INSERT INTO storageConfiguration (quantity, storage, configuration) VALUES (?, ?, ?)";
            try{
                for(HashMap.Entry<Storage, Integer> storage : storages.entrySet()) {
                    PreparedStatement psStorage = connection.prepareStatement(addStorageQuerry);
                    psStorage.setInt(1, storage.getValue());
                    psStorage.setString(2, storage.getKey().getName());
                    psStorage.setInt(3, configurationId);

                    psStorage.executeUpdate();
                }
            }catch(SQLException ex){
                throw new FailedToAddComponentException("configuration");
            }

            String addCoolingQuerry = "INSERT INTO coolingConfiguration (quantity, cooling, configuration) VALUES (?, ?, ?)";
            try{
                for(HashMap.Entry<Cooling, Integer> cooling : coolings.entrySet()) {
                    PreparedStatement psStorage = connection.prepareStatement(addCoolingQuerry);
                    psStorage.setInt(1, cooling.getValue());
                    psStorage.setString(2, cooling.getKey().getName());
                    psStorage.setInt(3, configurationId);

                    psStorage.executeUpdate();
                }
            }catch(SQLException ex){
                throw new FailedToAddComponentException("configuration");
            }
        }catch(SQLException e){
            throw new FailedToAddComponentException("configuration");
        }
    }
    @Override
    public int countConfiguration(Date date1, Date date2) {
        String querry = "SELECT COUNT(*) AS nbConfigurations FROM configuration WHERE creationDate BETWEEN ? AND ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(querry);
            ps.setDate(1, new java.sql.Date(date1.getTime()));
            ps.setDate(2, new java.sql.Date(date2.getTime()));
            ResultSet rs = ps.executeQuery();
            int nbConfigurations = 0;
            if(rs.next()){
                nbConfigurations = rs.getInt("nbConfigurations");
            }
            return nbConfigurations;
        } catch (SQLException e) {
            throw new FailedToGetComponentException("configuration");
        }
    }
}