package DAO.Configuration;

import DAO.SingletonConnection;
import Exceptions.FailedToAddComponentException;
import MVC.Model.Configuration;
import MVC.Model.CoolingConfiguration;
import MVC.Model.StorageConfiguration;

import java.sql.*;

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
}
