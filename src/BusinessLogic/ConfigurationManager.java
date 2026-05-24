package BusinessLogic;

import DAO.Configuration.ConfigurationDAO;
import DAO.Configuration.ConfigurationDAOImpl;
import MVC.Model.*;

import java.util.Date;

public class ConfigurationManager{
    private ConfigurationDAO configurationDAO;

    public ConfigurationManager() {
        this.configurationDAO = new ConfigurationDAOImpl();
    }

    public int addConfiguration(Configuration conf) {
        return configurationDAO.addConfiguration(conf);
    }
    public void addStorageConfiguration(StorageConfiguration storageConfiguration) {
        configurationDAO.addStorageConfiguration(storageConfiguration);
    }
    public void addCoolingConfiguration(CoolingConfiguration coolingConfiguration) {
        configurationDAO.addCoolingConfiguration(coolingConfiguration);
    }
    public int countConfiguration(Date date1, Date date2){
        return configurationDAO.countConfiguration(
                new java.sql.Date(date1.getTime()),
                new java.sql.Date(date2.getTime())
        );
    }
}

