package DAO.Configuration;

import MVC.Model.Configuration;
import MVC.Model.CoolingConfiguration;
import MVC.Model.StorageConfiguration;

import java.sql.Date;
import java.util.ArrayList;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);
    Configuration getConfiguration(int id);
    ArrayList<Configuration> getConfigurations();
}
