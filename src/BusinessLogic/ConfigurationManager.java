package BusinessLogic;

import DAO.Configuration.ConfigurationDAO;
import DAO.Configuration.ConfigurationDAOImpl;
import MVC.Model.*;

import java.util.Date;
import java.util.HashMap;

public class ConfigurationManager{
    private ConfigurationDAO configurationDAO;

    public ConfigurationManager() {
        this.configurationDAO = new ConfigurationDAOImpl();
    }

    public void addConfiguration(Configuration config, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings){
        configurationDAO.addConfiguration(config, storages, coolings);
    }
    public int countConfiguration(Date date1, Date date2){
        return configurationDAO.countConfiguration(
                new java.sql.Date(date1.getTime()),
                new java.sql.Date(date2.getTime())
        );
    }
}

